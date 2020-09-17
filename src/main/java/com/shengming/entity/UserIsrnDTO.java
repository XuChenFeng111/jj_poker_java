package com.shengming.entity;

/**
 * 实名注册
 * @author XuChenFeng
 * @Date 2020/8/21 14:36
 */
public class UserIsrnDTO {
    private Integer userid;
    private String realname;
    private String idCard;
    private String token;

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "UserIsrnDTO{" +
                "userid=" + userid +
                ", realname='" + realname + '\'' +
                ", idCard='" + idCard + '\'' +
                ", token='" + token + '\'' +
                '}';
    }
}
