package com.shengming.service;

import java.io.UnsupportedEncodingException;

/**
 * @author XuChenFeng
 * @Date 2020/8/14 13:24
 */
public interface AuthService {
    /**
     * 根据code获得Token
     *
     * @param code code
     * @return token
     */
    String getAccessToken(String code);
    /**
     * 根据Token获得OpenId
     *
     * @param accessToken Token
     * @return openId
     */
    String getOpenId(String accessToken);
    /**
     * 刷新Token
     *
     * @param code code
     * @return 新的token
     */
    String refreshToken(String refresh_token,String appId);
    /**
     * 拼接授权URL
     *
     * @return URL
     */
    String getAuthorizationUrl() throws UnsupportedEncodingException;
    /**
     * 根据Token和OpenId获得用户信息
     *
     * @param accessToken Token
     * @param openId openId
     * @return 第三方应用给的用户信息
     */
//    Response<BindUserDTO> getUserInfo(String accessToken, String openId);
}
