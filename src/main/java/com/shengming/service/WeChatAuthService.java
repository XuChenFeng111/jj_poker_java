package com.shengming.service;


import com.alibaba.fastjson.JSONObject;

/**
 * 微信登陆接口
 * @author XuChenFeng
 * @Date 2020/8/14 13:08
 */
public interface WeChatAuthService extends AuthService {
    public JSONObject getUserInfo(String accessToken, String openId);
}