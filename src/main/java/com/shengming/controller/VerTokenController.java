package com.shengming.controller;

import com.shengming.dao.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * 验证token信息
 * @author XuChenFeng
 * @Date 2020/8/25 15:05
 */
@RestController
public class VerTokenController {

    public static final String TOKEN_FAILURE = "token失效"; // 失效
    public static final String TOKEN_SUCCESS = "token验证成功"; // 成功

    @Autowired
    private UserMapper userMapper;


    @RequestMapping("/getToken")
    public String getToken(String userid, String token) {
        String userToken = userMapper.getToken(userid);
        if (!userToken.equals(token)) {
            return TOKEN_FAILURE;
        }
        return TOKEN_SUCCESS;
    }
}
