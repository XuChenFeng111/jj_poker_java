package com.shengming.entity;


/**
 * @author XuChenFeng
 * @Date 2020/8/28 10:32
 */
public class TokenDTO {

    private String token;

    private Integer userId;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "TokenDTO{" +
                "token='" + token + '\'' +
                ", userId=" + userId +
                '}';
    }
}
