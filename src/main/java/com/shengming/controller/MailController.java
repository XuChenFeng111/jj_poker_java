package com.shengming.controller;

import com.shengming.constants.BaseEnums;
import com.shengming.dao.UserMapper;
import com.shengming.entity.MailDTO;
import com.shengming.entity.MailDetailsDTO;
import com.shengming.entity.UserDetailsDTO;
import com.shengming.entity.result.Result;
import com.shengming.service.MailService;
import com.shengming.utils.CountDiffTimeUtils;
import com.shengming.utils.ResultsUtils;
import com.shengming.utils.VerTokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

/**
 * @author XuChenFeng
 * @Date 2020/9/2 15:28
 */
@RestController
@RequestMapping("/mail")
@Transactional
public class MailController {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private VerTokenUtils verTokenUtils;
    @Autowired
    private MailService mailService;
    @Autowired
    private CountDiffTimeUtils countDiffTimeUtils;
    @Value("${mail.point}")
    private Integer point;

    /**
     * 赠送还有金豆 发送邮件
     *
     * @param userid
     * @param friendid
     * @param token
     * @return
     */
    @RequestMapping("/sendMailTofriend")
    public Result sendMailTofriend(Integer userid, Integer friendid, String token) {
        //验证token 0：成功 ；1：失败
        String tokenByUserId = userMapper.findTokenByUserId(userid);
        String isok = verTokenUtils.verToken(token, tokenByUserId);
        if (isok.equals("0")) {
            Integer integer = setValue(userid, friendid);
            if (integer == 0) {
                return ResultsUtils.successWithData("", BaseEnums.SUCCESS.code(), "赠送好友金豆成功");
            } else {
                return ResultsUtils.successWithData("", "201","赠送失败或者今日已赠送");
            }
        } else {
            return ResultsUtils.failureWithData(BaseEnums.TOKEN_FAILES.code(), BaseEnums.TOKEN_FAILES.desc());
        }
    }

    /**
     * 给mailDTO赋值
     *
     * @param userid
     * @param friendid
     * @return 0 成功 1 失败
     */
    public Integer setValue(Integer userid, Integer friendid) {
        MailDTO mailDTO = new MailDTO();
        mailDTO.setMailType(1);
        //礼物类型 2为 金豆
        mailDTO.setGifttpye(2);
        mailDTO.setUserid(userid);
        mailDTO.setFriendid(friendid);
        mailDTO.setIfsee(1);
        mailDTO.setGiftnum(300);
        mailDTO.setIfSend(1);
        //判断今日是否已经赠送
        if (mailService.ifTodaySend(userid, friendid) == null) {
            int insert = mailService.insert(mailDTO);
            if (insert == 1) {
                return 0;
            } else {
                return 1;
            }
        } else {
            return 1;
        }
    }


    /**
     * 查询好友邮件列表
     *
     * @param userid
     * @param token
     * @return
     */
    @RequestMapping("/findMailFromFriendList")
    public Result findMailFromFriendList(Integer userid, String token) {
        //验证token 0：成功 ；1：失败
        String tokenByUserId = userMapper.findTokenByUserId(userid);
        String isok = verTokenUtils.verToken(token, tokenByUserId);
        if (isok.equals("0")) {
            //查询好友邮件列表
            List<MailDetailsDTO> mailFromFriendList = mailService.findMailFromFriendList(userid);
            for (MailDetailsDTO mailDetailsDTO : mailFromFriendList) {
                Date mailSendtime = mailDetailsDTO.getMailSendtime();
                mailDetailsDTO.setUserid(userid);
                long countTime = countDiffTimeUtils.countTime(mailSendtime);
                long minutes = (countTime % (1000 * 60 * 60)) / (1000 * 60);
                long hours = (countTime % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60);
                long days = countTime / (1000 * 60 * 60 * 24);
                if (days > 0) {
                    mailDetailsDTO.setDiffTime(days + "天");
//                    break;
                } else if (0 < hours && hours < 24) {
                    mailDetailsDTO.setDiffTime(hours + "小时");
                } else if (0 < minutes && minutes < 60) {
                    mailDetailsDTO.setDiffTime(minutes + "分钟");
                } else {

                }
            }
            return ResultsUtils.successWithData(mailFromFriendList, BaseEnums.SUCCESS.code(), "查询好友邮件列表成功");
        } else {
            return ResultsUtils.failureWithData(BaseEnums.TOKEN_FAILES.code(), BaseEnums.TOKEN_FAILES.desc());
        }
    }

    /**
     * @param userid
     * @param type   0:单个领取 1：一键领取
     * @param token
     * @return
     */
    @RequestMapping("/receiveAndSend")
    public Result receiveAndSend(Integer userid, Integer mailId, Integer type, String token) {
        //验证token 0：成功 ；1：失败
        String tokenByUserId = userMapper.findTokenByUserId(userid);
        String isok = verTokenUtils.verToken(token, tokenByUserId);
        if (isok.equals("0")) {
            //单个领取并回赠
            if (type == 0) {
                Integer friendid = mailService.findUserIdByMailId(mailId);
                setValue(userid, friendid);
                updateStatusAndAmoint(mailId, userid);
                return ResultsUtils.successWithData("", BaseEnums.SUCCESS.code(), "领取并回赠成功");
            }
            if (type == 1) {
                //查出该用户在数据库中的未查看的邮件的发件人id  userid=10  friendid=1，2
//                List<Integer> friendids = mailService.findIds(userid);
                List<MailDetailsDTO> mailFromFriendList = mailService.findMailFromFriendList(userid);
                for (MailDetailsDTO mailDetailsDTO : mailFromFriendList) {
                    Integer friendid2 = mailDetailsDTO.getFriendid();
                    Integer mailId2 = mailDetailsDTO.getMailId();
                    setValue(userid, friendid2);
                    updateStatusAndAmoint(mailId2, userid);
                }
                return ResultsUtils.successWithData("", BaseEnums.SUCCESS.code(), "一键领取并回赠成功");
            }
        }
        return ResultsUtils.success();
    }

    /**
     * 接受邮件 并更改邮件查看状态和用户账户余额
     *
     * @param mailId
     * @param userid
     * @return 0 成功 1 失败
     */
    public void updateStatusAndAmoint(Integer mailId, Integer userid) {
        try {
            //修改邮件状态
            mailService.updateStatus(mailId);
            //更改账户余额
            UserDetailsDTO userDetailsDTO = userMapper.selectByPrimaryKey(userid);
            //账户余额
            Integer pointFromDB = userDetailsDTO.getPoint();
            pointFromDB = pointFromDB + point;
            userDetailsDTO.setPoint(pointFromDB);
            userMapper.updateAmount(userDetailsDTO);
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            e.printStackTrace();
        }
    }
}



