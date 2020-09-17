package com.shengming.utils;

import org.springframework.stereotype.Component;

/**
 * @author XuChenFeng
 * @Date 2020/8/28 13:19
 */
@Component
public class VerTokenUtils {

    /**
    * 验证token
     * 0是验证成功
     * 1是验证失败
    * */
    public String verToken(String oldToken, String newToken) {
        String isok = "1";
        try {
            if (oldToken.equals(newToken)) {
                isok = "0";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //isok="0";
        return isok;
    }
}
