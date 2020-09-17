package com.shengming.controller;

import com.shengming.constants.BaseEnums;
import com.shengming.dao.LotteryGiftMapper;
import com.shengming.dao.LotteryMapper;
import com.shengming.dao.UserMapper;
import com.shengming.entity.LotteryGiftDTO;
import com.shengming.entity.UserDetailsDTO;
import com.shengming.entity.result.Result;
import com.shengming.utils.ResultsUtils;
import com.shengming.utils.VerTokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author donggege
 * @create 2020-08-27 11:19
 */
@RestController
@RequestMapping("/lottery")
public class LotteryController {

    @Autowired
    private LotteryMapper lotteryMapper;
    @Autowired
    private VerTokenUtils verTokenUtils;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private LotteryGiftMapper lotteryGiftMapper;

    @Value("${lottery.lotteryGoldcoin}")
    private Integer lotteryGoldcoin;


    /**
     * 转盘抽奖 消耗金币
     *
     * @param userid
     * @return
     */
    @RequestMapping("/update_lottery")
    public Result update_lottery(Integer userid, String token) {
        //验证token 0：成功 ；1：失败
        String tokenByUserId = userMapper.findTokenByUserId(userid);
        String isok = verTokenUtils.verToken(token, tokenByUserId);
        if (isok.equals("0")) {
            UserDetailsDTO userDetailsDTO = userMapper.selectByPrimaryKey(userid);
            //数据库中的金币数量
            Integer goldcoinFromData = userDetailsDTO.getGoldcoin();
            Integer pointFromData = userDetailsDTO.getPoint();
            //确保账号中的金币够抽奖
            if (goldcoinFromData >= lotteryGoldcoin) {
                //先扣除抽奖所需的金币
                goldcoinFromData = goldcoinFromData - lotteryGoldcoin;
//                List<LotteryGiftDTO> gifts = lotteryGiftMapper.findAllList();
                Integer random = percentageRandom();
                LotteryGiftDTO lotteryGiftDTO = lotteryGiftMapper.findGiftNameById(random);
                String giftName = lotteryGiftDTO.getGiftName();
                if (giftName.contains("金币")) {
                    //抽奖所得金币数
                    Integer goldcoinFromLottery = Integer.valueOf(giftName.replaceAll("\\D", ""));
                    goldcoinFromData = goldcoinFromData + goldcoinFromLottery;
                }
                if (giftName.contains("金豆")) {
                    //抽奖所得金币数
                    Integer pointFromLottery = Integer.valueOf(giftName.replaceAll("\\D", ""));
                    pointFromData = pointFromData + pointFromLottery;
                }
                if (lotteryMapper.update_lottery(userid, pointFromData, goldcoinFromData)) {
                    return ResultsUtils.successWithData(lotteryGiftDTO, BaseEnums.SUCCESS.code(), "抽奖保存成功");
                } else {
                    return ResultsUtils.successWithData("", "201","抽奖保存失败");
                }
            } else {
                return ResultsUtils.successWithData("", "201","金币不足，请充值");
            }
        } else {
            return ResultsUtils.failureWithData(BaseEnums.TOKEN_FAILES.code(), BaseEnums.TOKEN_FAILES.desc());
        }
    }



    /**
     * Math.random()产生一个double型的随机数，判断一下
     * 例如0出现的概率为%50，则介于0到0.50中间的返回0
     *
     * @return int
     */
    public Integer percentageRandom(){
        //0.35 再接再厉
        double rate1 = lotteryGiftMapper.findProbability("P1");
        //0.05 300金豆
        double rate2 = lotteryGiftMapper.findProbability("P2");
        //0.3 500金豆
        double rate3 = lotteryGiftMapper.findProbability("P3");
        //0.2 800金豆
        double rate4 = lotteryGiftMapper.findProbability("P4");
        //0.1 1000金豆
        double rate5 = lotteryGiftMapper.findProbability("P5");
        //0.004 300金币
        double rate6 = lotteryGiftMapper.findProbability("P6");
        //0.008 500金币
        double rate7 = lotteryGiftMapper.findProbability("P7");

        double randomNumber;
        randomNumber = Math.random();
        if (randomNumber >= 0 && randomNumber <= rate1) {
            return 1;
        } else if (randomNumber >= rate1 / 100 && randomNumber <= rate1 + rate2) {
            return 2;
        } else if (randomNumber >= rate1 + rate2
                && randomNumber <= rate1 + rate2 + rate3) {
            return 3;
        } else if (randomNumber >= rate1 + rate2 + rate3
                && randomNumber <= rate1 + rate2 + rate3 + rate4) {
            return 4;
        } else if (randomNumber >= rate1 + rate2 + rate3 + rate4
                && randomNumber <= rate1 + rate2 + rate3 + rate4 + rate5) {
            return 5;
        } else if (randomNumber >= rate1 + rate2 + rate3 + rate4 + rate5
                && randomNumber <= rate1 + rate2 + rate3 + rate4 + rate5 + rate6) {
            return 6;
        } else if (randomNumber >= rate1 + rate2 + rate3 + rate4 + rate5 + rate6
                && randomNumber <= rate1 + rate2 + rate3 + rate4 + rate5 + rate6 + rate7) {
            return 7;
        }
        return -1;
    }
}
