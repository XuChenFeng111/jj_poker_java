package com.shengming.controller;

import com.shengming.constants.BaseEnums;
import com.shengming.dao.UserMapper;
import com.shengming.entity.MallGoodsDTO;
import com.shengming.entity.OrderDTO;
import com.shengming.entity.UserDetailsDTO;
import com.shengming.entity.result.Result;
import com.shengming.service.MallGoodsService;
import com.shengming.service.OrderService;
import com.shengming.utils.ResultsUtils;
import com.shengming.utils.VerTokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * 购买商品
 * @author XuChenFeng
 * @Date 2020/9/2 13:20
 */
@RestController
@RequestMapping("/order")
@Transactional
public class OrderController {

    @Autowired
    private OrderService orderService;
    @Autowired
    private MallGoodsService mallGoodsService;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private VerTokenUtils verTokenUtils;

    @RequestMapping("/createOrder")
    public Result createOrder(Integer userid, String token, Integer goodsId) {
        //验证token 0：成功 ；1：失败
        String tokenByUserId = userMapper.findTokenByUserId(userid);
        String isok = verTokenUtils.verToken(token, tokenByUserId);
        if (isok.equals("0")) {
            UserDetailsDTO userDetailsDTO = userMapper.selectByPrimaryKey(userid);
            //金豆
            Integer point = userDetailsDTO.getPoint();
            //金币
            Integer goldcoin = userDetailsDTO.getGoldcoin();
            //福卡
            Integer fukaNum = userDetailsDTO.getFukaNum();
            Integer recommenderId = userDetailsDTO.getRecommenderId();
            //数据库中用户的账户余额
            Integer amount = userDetailsDTO.getAmount();
            //商城中的最小单价
            Integer minPrice = mallGoodsService.findMinPrice();
            MallGoodsDTO goods = mallGoodsService.findGoodsById(goodsId);
            if (amount >= minPrice && amount >= goods.getUnitprice()) {
                switch (goods.getCategory()) {
                    case 1:
                        goldcoin = goldcoin + goods.getGoodsName();
                        amount = amount - goods.getUnitprice();
                        userDetailsDTO.setGoldcoin(goldcoin);
                        userDetailsDTO.setAmount(amount);
                        break;
                    case 2:
                        point = point + goods.getGoodsName();
                        amount = amount - goods.getUnitprice();
                        userDetailsDTO.setPoint(point);
                        userDetailsDTO.setAmount(amount);
                        break;
                    case 3:
                        fukaNum = fukaNum + goods.getGoodsName();
                        amount = amount - goods.getUnitprice();
                        userDetailsDTO.setFukaNum(fukaNum);
                        userDetailsDTO.setAmount(amount);
                    default:
                        break;
                }
                userMapper.updateAmount(userDetailsDTO);
                OrderDTO order = new OrderDTO();
                //生成订单id
                order.setOrderId(UUID.randomUUID().toString().replace("-", ""));
                order.setMemberFkRecommenderId(recommenderId);
                order.setMallFkGoodsId(goodsId);
                order.setMemberFkUserId(userid);
                int insert = orderService.insert(order);
                if (insert == 1) {
                    return ResultsUtils.successWithData("",BaseEnums.SUCCESS.code(), "购买成功");
                } else {
                    TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                    return ResultsUtils.successWithData("","201","购买失败");
                }
            } else {
                return ResultsUtils.successWithData("","201","余额不足，请充值");
            }
        } else {
            return ResultsUtils.failureWithData(BaseEnums.TOKEN_FAILES.code(), BaseEnums.TOKEN_FAILES.desc());
        }
    }
}
