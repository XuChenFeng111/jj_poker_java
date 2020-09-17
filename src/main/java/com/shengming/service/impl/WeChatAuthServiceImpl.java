package com.shengming.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.shengming.constants.BaseEnums;
import com.shengming.dao.UserMapper;
import com.shengming.entity.TokenDTO;
import com.shengming.entity.UserDTO;
import com.shengming.service.ServiceException;
import com.shengming.service.WeChatAuthService;
import com.shengming.utils.ResultsUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLEncoder;
import java.sql.Timestamp;
import java.util.Date;
import java.util.UUID;

/**
 * @author XuChenFeng
 * @Date 2020/8/14 13:11
 */
@Service
public class WeChatAuthServiceImpl extends DefaultAuthServiceImpl implements WeChatAuthService {

    private Logger logger = LoggerFactory.getLogger(WeChatAuthServiceImpl.class);

    //请求此地址即跳转到二维码登录界面
    private static final String AUTHORIZATION_URL =
            "https://open.weixin.qq.com/connect/qrconnect?appid=%s&redirect_uri=%s&response_type=code&scope=%s&state=%s#wechat_redirect";

    // 获取用户 openid 和access——toke 的 URL
    private static final String ACCESSTOKE_OPENID_URL =
            "https://api.weixin.qq.com/sns/oauth2/access_token?appid=%s&secret=%s&code=%s&grant_type=authorization_code";

    private static final String REFRESH_TOKEN_URL =
            "https://api.weixin.qq.com/sns/oauth2/refresh_token?appid=%s&grant_type=refresh_token&refresh_token=%s";

    private static final String USER_INFO_URL =
            "https://api.weixin.qq.com/sns/userinfo?access_token=%s&openid=%s&lang=zh_CN";

    private static final String APP_ID = "wx24b6b13e77e1bbb2";
    private static final String APP_SECRET = "47214f430a3d3231d623630e9237d4e3";
    private static final String SCOPE = "snsapi_userinfo";

    private String callbackUrl = "https://www.baidu.com/auth/wechat"; //回调域名

//    private String access_token;
//    private String openId;

    @Autowired
    private UserMapper userMapper;

    @Override
    public String getAuthorizationUrl() throws UnsupportedEncodingException {
        callbackUrl = URLEncoder.encode(callbackUrl, "utf-8");
        String url = String.format(AUTHORIZATION_URL, APP_ID, callbackUrl, SCOPE, System.currentTimeMillis());
        return url;
    }


    @Override
    public String getAccessToken(String code) {
//        String url = String.format(ACCESSTOKE_OPENID_URL,APP_ID,APP_SECRET,code);
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(code);
        URI uri = builder.build().encode().toUri();

        String resp = getRestTemplate().getForObject(uri, String.class);
        logger.error("getAccessToken resp = " + resp);
        if (resp.contains("openid")) {
            JSONObject jsonObject = JSONObject.parseObject(resp);
            String access_token = jsonObject.getString("access_token");
            String openId = jsonObject.getString("openid");

            JSONObject res = new JSONObject();
            res.put("access_token", access_token);
            res.put("openId", openId);
            res.put("refresh_token", jsonObject.getString("refresh_token"));

            return res.toJSONString();
        } else {
            throw new ServiceException("获取token失败，msg = " + resp);
        }
    }

    //微信接口中，token和openId是一起返回，故此方法不需实现
    @Override
    public String getOpenId(String accessToken) {
        return null;
    }

    @Override
    public JSONObject getUserInfo(String accessToken, String openId) {
        String url = String.format(USER_INFO_URL, accessToken, openId);
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);
        URI uri = builder.build().encode().toUri();

        String resp = getRestTemplate().getForObject(uri, String.class);
        logger.error("getUserInfo resp = " + resp);
        if (resp.contains("errcode")) {
            throw new ServiceException("获取用户信息错误，msg = " + resp);
        } else {
            JSONObject data = JSONObject.parseObject(resp);

            JSONObject result = new JSONObject();
            result.put("id", data.getString("unionid"));
            result.put("nickName", data.getString("nickname"));
            result.put("avatar", data.getString("headimgurl"));
            result.put("gender", data.getString("sex"));

            Integer userId = 0;
            String token = null;
            //判断数据库中是否存储
            String ifOpenId = userMapper.ifOpenId(openId);
            if (ifOpenId == null) {
                UserDTO userDTO = new UserDTO();
                //随机生成token
                token = UUID.randomUUID().toString().replace("-", "");
                // 将获取到的用户信息和token等信息 存储数据库
                userDTO.setToken(token);
                userDTO.setOpenid(openId);
                userDTO.setNickname(result.getString("nickName"));
                userDTO.setAvatar(result.getString("avatar"));
                userDTO.setGender(result.getInteger("gender"));
                userDTO.setLogonType(1);
                userDTO.setIsrn(0);
                userDTO.setPoint(0);
                userDTO.setGoldcoin(0);
                userDTO.setFukanum(0);
                userDTO.setLevel(1);
                userDTO.setIsvip(0);
                userDTO.setIfphover(0);
                userDTO.setRegtime(new Timestamp(new Date().getTime()));
                userDTO.setAccessToken(accessToken);
//                userDTO.setAccessExpiretoken();
//                userDTO.setRefreshToken();
                userMapper.insert(userDTO);
                userId = userMapper.findUserIdByOpenId(openId);
                TokenDTO tokenDTO = new TokenDTO();
                //查询出新用户的token和userid 进行返回
                tokenDTO.setToken(token);
                tokenDTO.setUserId(userId);
                result.put("token", token);
                result.put("userId", userId);
            } else {
                //随机生成token
                token = UUID.randomUUID().toString().replace("-", "");
                userMapper.updateTokenForVX(token, openId);
                userId = userMapper.findUserIdByOpenId(openId);
                TokenDTO tokenDTO = new TokenDTO();
                //查询出新用户的token和userid 进行返回
                tokenDTO.setToken(token);
                tokenDTO.setUserId(userId);
                result.put("token", token);
                result.put("userId", userId);
            }
            return result;
        }
    }

    //微信的token只有2小时的有效期，过时需要重新获取，所以官方提供了根据refresh_token刷新获取token的方法，
    // 本项目仅仅是获取用户信息，并将信息存入库，所以两个小时也已经足够了
    @Override
    public String refreshToken(String refresh_token, String appId) {

        String url = String.format(REFRESH_TOKEN_URL, appId, refresh_token);

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);
        URI uri = builder.build().encode().toUri();

        ResponseEntity<JSONObject> resp = getRestTemplate().getForEntity(uri, JSONObject.class);
        JSONObject jsonObject = resp.getBody();

        String access_token = jsonObject.getString("access_token");
        return access_token;
    }
}
