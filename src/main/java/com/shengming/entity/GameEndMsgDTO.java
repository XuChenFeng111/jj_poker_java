package com.shengming.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * @author XuChenFeng
 * @Date 2020/9/7 15:43
 */
public class GameEndMsgDTO implements Serializable {
    private Integer userid;
    private Integer point;
    private Integer goldCoin;
    private String token;
    private Integer iswin;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss" ,timezone="GMT+8")
    private Date beginTime;
//    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
//    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
//    private Date endTime;

    private Integer gametype;

    public Integer getGametype() {
        return gametype;
    }

    public void setGametype(Integer gametype) {
        this.gametype = gametype;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Integer getPoint() {
        return point;
    }

    public void setPoint(Integer point) {
        this.point = point;
    }

    public Integer getGoldCoin() {
        return goldCoin;
    }

    public void setGoldCoin(Integer goldCoin) {
        this.goldCoin = goldCoin;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Integer getIswin() {
        return iswin;
    }

    public void setIswin(Integer iswin) {
        this.iswin = iswin;
    }

    public Date getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

//    public Date getEndTime() {
//        return endTime;
//    }
//
//    public void setEndTime(Date endTime) {
//        this.endTime = endTime;
//    }

    @Override
    public String toString() {
        return "GameEndMsgDTO{" +
                "userid=" + userid +
                ", point=" + point +
                ", goldCoin=" + goldCoin +
                ", token='" + token + '\'' +
                ", iswin=" + iswin +
                ", beginTime=" + beginTime +
                '}';
    }
}
