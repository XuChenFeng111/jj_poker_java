package com.shengming.controller;

import com.shengming.constants.BaseEnums;
import com.shengming.dao.UserMapper;
import com.shengming.entity.MallGoodsDTO;
import com.shengming.entity.result.BaseEnum;
import com.shengming.entity.result.Result;
import com.shengming.service.MallGoodsService;
import com.shengming.utils.ResultsUtils;
import com.shengming.utils.VerTokenUtils;
import org.omg.CORBA.PRIVATE_MEMBER;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author XuChenFeng
 * @Date 2020/9/2 9:45
 */
@RestController
@RequestMapping("/mall")
public class MallGoodsController {

    @Autowired
    private MallGoodsService mallGoodsService;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private VerTokenUtils verTokenUtils;


    @RequestMapping("/searchGoods")
    public Result findAll(Integer category,String token,Integer userid){
        //验证token 0：成功 ；1：失败
        String tokenByUserId = userMapper.findTokenByUserId(userid);
        String isok = verTokenUtils.verToken(token, tokenByUserId);
        if (isok.equals("0")) {
            List<MallGoodsDTO> all = mallGoodsService.findAll(category);
            return ResultsUtils.successWithData(all, BaseEnums.SUCCESS.code(),"获取商品列表成功");
        } else {
            return ResultsUtils.failureWithData(BaseEnums.TOKEN_FAILES.code(),BaseEnums.TOKEN_FAILES.desc());
        }
    }
}
