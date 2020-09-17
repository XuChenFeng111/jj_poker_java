package com.shengming.controller;

import com.shengming.constants.BaseEnums;
import com.shengming.dao.UserMapper;
import com.shengming.entity.ConsumeDTO;
import com.shengming.entity.UserDetailsDTO;
import com.shengming.entity.result.Result;
import com.shengming.service.ConsumeService;
import com.shengming.utils.ResultsUtils;
import com.shengming.utils.VerTokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 奖品兑换中心
 *
 * @author XuChenFeng
 * @Date 2020/9/14 10:00
 */
@RestController
@RequestMapping("/consume")
@Transactional
public class ConsumeController {

    @Autowired
    private VerTokenUtils verTokenUtils;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private ConsumeService consumeService;

    @RequestMapping("/getAllList")
    public Result getAllList(String token, Integer userid) {
        //验证token 0：成功 ；1：失败
        String tokenByUserId = userMapper.findTokenByUserId(userid);
        String isok = verTokenUtils.verToken(token, tokenByUserId);
        if (isok.equals("0")) {
            List<ConsumeDTO> all = consumeService.findAll();
            return ResultsUtils.successWithData(all, BaseEnums.SUCCESS.code(), BaseEnums.SUCCESS.desc());
        } else {
            return ResultsUtils.failureWithData(BaseEnums.TOKEN_FAILES.code(), BaseEnums.TOKEN_FAILES.desc());
        }
    }


    @RequestMapping("/consumeGift")
    public Result consumeGift(String token, Integer userid, Integer convertid) {
        //验证token 0：成功 ；1：失败
        String tokenByUserId = userMapper.findTokenByUserId(userid);
        String isok = verTokenUtils.verToken(token, tokenByUserId);
        if (isok.equals("0")) {
            ConsumeDTO consumeDTO = consumeService.findById(convertid);
            //兑换的礼品
            String convertgift = consumeDTO.getConvertgift();
            Integer value = Integer.valueOf(convertgift.replaceAll("\\D", ""));
            //兑换消耗的福卡
            Integer convertConsume = consumeDTO.getConvertConsume();
            UserDetailsDTO userDetailsDTO = userMapper.selectByPrimaryKey(userid);
            //账户余额的金币和福卡数量
            Integer goldcoinFromDB = userDetailsDTO.getGoldcoin();
            Integer fukaNumFromDB = userDetailsDTO.getFukaNum();
            if (fukaNumFromDB >= convertConsume ) {
                Integer goldcoinDiff = goldcoinFromDB + value;
                Integer fukaNumDiff = fukaNumFromDB - convertConsume;
                userDetailsDTO.setGoldcoin(goldcoinDiff);
                userDetailsDTO.setFukaNum(fukaNumDiff);
                userMapper.updateAmount(userDetailsDTO);
                return ResultsUtils.successWithData("", BaseEnums.SUCCESS.code(), "兑换成功");
            } else {
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                return ResultsUtils.successWithData("", "201","福卡数量不足");
            }
        } else {
            return ResultsUtils.failureWithData(BaseEnums.TOKEN_FAILES.code(), BaseEnums.TOKEN_FAILES.desc());
        }
    }
}
