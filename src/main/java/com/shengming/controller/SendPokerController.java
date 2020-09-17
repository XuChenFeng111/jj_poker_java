package com.shengming.controller;

import com.shengming.constants.BaseEnums;
import com.shengming.entity.CardDTO;
import com.shengming.entity.result.Result;
import com.shengming.utils.ResultsUtils;
import com.shengming.utils.SendPokerUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @author XuChenFeng
 * @Date 2020/9/12 10:10
 */
@CrossOrigin
@RestController
@RequestMapping("/game")
public class SendPokerController {

    @Autowired
    private SendPokerUtils sendPokerUtils;


    @RequestMapping("/sendPoker")
    public Result sendPoker(Integer userid) {
        Map<String, List<CardDTO>> stringListMap = sendPokerUtils.sendPoker(userid);
        if (stringListMap != null) {
            return ResultsUtils.successWithData(stringListMap, BaseEnums.SUCCESS.code(), "分发牌成功");
        }else {
            return ResultsUtils.successWithData("","201","发牌发生错误");
        }
    }
}
