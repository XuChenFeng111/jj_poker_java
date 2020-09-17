package com.shengming.controller;

import com.shengming.constants.BaseEnums;
import com.shengming.dao.UserMapper;
import com.shengming.entity.UserDetailsDTO;
import com.shengming.entity.result.Result;
import com.shengming.service.UserDetailsService;
import com.shengming.utils.IdentityIDCardUtils;
import com.shengming.utils.RandomUtils;
import com.shengming.utils.ResultsUtils;
import com.shengming.utils.VerTokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author XuChenFeng
 * @Date 2020/8/20 13:13
 */
@RestController
@RequestMapping("/user")
public class GetUserDetailsController {

    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private VerTokenUtils verTokenUtils;

    @RequestMapping("/getUserDetails")
    public Result getUser(Integer userid, String token) {
        //验证token 0：成功 ；1：失败
        String tokenByUserId = userMapper.findTokenByUserId(userid);
        String isok = verTokenUtils.verToken(token, tokenByUserId);
        if (isok.equals("0")) {
            //查询用户详情
            UserDetailsDTO userDetails = userDetailsService.getUserDetails(userid);
            //获取该用户推荐的好友数量
            int recomNum = userMapper.findRecomNum(userid);
            if (recomNum == 0) {
                //如果没有推荐好友 默认数量为0
                userDetails.setRecomNum(0);
            }
            userDetails.setRecomNum(recomNum);
            //用户常玩游戏
            userDetails.setOftengame(userDetailsService.findOftenGame(userid));
            if (userDetails != null) {
                return ResultsUtils.successWithData(userDetails, BaseEnums.SUCCESS.code(), BaseEnums.SUCCESS.desc());
            } else {
                return ResultsUtils.successWithData("", "201", "未查询到用户");
            }
        } else {
            return ResultsUtils.failureWithData(BaseEnums.TOKEN_FAILES.code(), BaseEnums.TOKEN_FAILES.desc());
        }
    }


    /**
     * 实名注册
     */
    @RequestMapping("/isrnRegist")
    public Result isrnRegist(Integer userId, String realname, String idCard, String token) {
        //获取该用户详情
        UserDetailsDTO userDetails = userDetailsService.getUserDetails(userId);
        //验证token 0：成功 ；1：失败
        String tokenByUserId = userMapper.findTokenByUserId(userId);
        String isok = verTokenUtils.verToken(token, tokenByUserId);
        if (isok.equals("0")) {
            if (!realname.equals("") && !idCard.equals("")) {
                //校验身份证
                boolean idNumber = IdentityIDCardUtils.strongVerifyIdNumber(idCard);
                if (idNumber) {
                    Integer isrn = userDetails.getIsrn();
                    // 0：未注册；1已注册；2：正在审核
                    if (isrn == 0) {
                        userDetailsService.updateIsrn(userId, realname, idCard, token);
                        return ResultsUtils.successWithData("", BaseEnums.SUCCESS.code(), "实名注册成功");
                    } else if (isrn == 2) {
                        return ResultsUtils.successWithData("", BaseEnums.SUCCESS.code(), "正在审核");
                    } else {
                        return ResultsUtils.successWithData("", "201", "已实名注册");
                    }
                } else {
                    return ResultsUtils.successWithData("", "201","身份证号码格式不正确，请重新输入");
                }
            } else {
                return ResultsUtils.successWithData("", "201","未填入身份证号码或者真实姓名");
            }
        } else {
            return ResultsUtils.failureWithData(BaseEnums.TOKEN_FAILES.code(), BaseEnums.TOKEN_FAILES.desc());
        }
    }

    @RequestMapping("/updateNickname")
    public Result updateNickname(Integer userId, String avatar, String nickName, Integer gender, String token) {
        //验证token
        String tokenByUserId = userMapper.findTokenByUserId(userId);
        String isok = verTokenUtils.verToken(token, tokenByUserId);
        if (isok.equals("0")) {
            String ifNickname = userMapper.ifNickname(nickName);
            //查看是否有重复昵称
            if (ifNickname == null) {
                userDetailsService.updateNickname(userId, avatar, nickName, gender);
                return ResultsUtils.successWithData("", BaseEnums.SUCCESS.code(),"更换成功");
            } else {
                return ResultsUtils.successWithData("", "201","昵称已存在，请更换其他昵称");
            }
        } else {
            return ResultsUtils.failureWithData(BaseEnums.TOKEN_FAILES.code(), BaseEnums.TOKEN_FAILES.desc());
        }
    }

    /**
     * 用户下线
     *
     * @param userId
     * @param token
     * @return
     */
    @RequestMapping("/offline")
    public Result offline(Integer userId, String token) {
        //验证token
        String tokenByUserId = userMapper.findTokenByUserId(userId);
        String isok = verTokenUtils.verToken(token, tokenByUserId);
        if (isok.equals("0")) {
            userMapper.offline(userId);
            return ResultsUtils.successWithData("", "200", "下线成功");
        } else {
            return ResultsUtils.failureWithData(BaseEnums.TOKEN_FAILES.code(), BaseEnums.TOKEN_FAILES.desc());
        }
    }

    /**
     * zhuyidong
     * 推荐人更新
     *
     * @param userid
     * @param recommenderId
     * @return
     */
    @RequestMapping("/update_Recommender")
    public Result update_Recommender(Integer userid, Integer recommenderId) {
        UserDetailsDTO userDetails = userDetailsService.getUserDetails(userid);
        if (userDetails.getRecommenderId() != null) {
            return ResultsUtils.failure("推荐失败，该用户已注册！");
        } else {
            if (userDetailsService.update_Recommender(userid, recommenderId)) {
                return ResultsUtils.success("推荐成功");
            } else {
                return ResultsUtils.failure("推荐失败");
            }
        }
    }

}
