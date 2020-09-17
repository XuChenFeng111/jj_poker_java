package com.shengming.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

public class GameScoreDTO implements Serializable {

    private Integer memberFkUserId;

    private Integer goldenBeansBefore;

    private Integer goldenCoinsBefore;

    private Integer goldenBeansAfter;

    private Integer goldenCoinsAfter;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Timestamp gamebegintime;
//    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
//    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date gameendtime;

    private Integer iswin;

    private Integer goldenBeansValue;

    private Integer goldenCoinsValue;

    private Integer gametype;

    private static final long serialVersionUID = 1L;


    public Integer getMemberFkUserId() {
        return memberFkUserId;
    }

    public void setMemberFkUserId(Integer memberFkUserId) {
        this.memberFkUserId = memberFkUserId;
    }

    public Integer getGoldenBeansBefore() {
        return goldenBeansBefore;
    }

    public void setGoldenBeansBefore(Integer goldenBeansBefore) {
        this.goldenBeansBefore = goldenBeansBefore;
    }

    public Integer getGoldenCoinsBefore() {
        return goldenCoinsBefore;
    }

    public void setGoldenCoinsBefore(Integer goldenCoinsBefore) {
        this.goldenCoinsBefore = goldenCoinsBefore;
    }

    public Integer getGoldenBeansAfter() {
        return goldenBeansAfter;
    }

    public void setGoldenBeansAfter(Integer goldenBeansAfter) {
        this.goldenBeansAfter = goldenBeansAfter;
    }

    public Integer getGoldenCoinsAfter() {
        return goldenCoinsAfter;
    }

    public void setGoldenCoinsAfter(Integer goldenCoinsAfter) {
        this.goldenCoinsAfter = goldenCoinsAfter;
    }

    public Timestamp getGamebegintime() {
        return gamebegintime;
    }

    public void setGamebegintime(Timestamp gamebegintime) {
        this.gamebegintime = gamebegintime;
    }

    public Date getGameendtime() {
        return gameendtime;
    }

    public void setGameendtime(Date gameendtime) {
        this.gameendtime = gameendtime;
    }

    public Integer getIswin() {
        return iswin;
    }

    public void setIswin(Integer iswin) {
        this.iswin = iswin;
    }

    public Integer getGoldenBeansValue() {
        return goldenBeansValue;
    }

    public void setGoldenBeansValue(Integer goldenBeansValue) {
        this.goldenBeansValue = goldenBeansValue;
    }

    public Integer getGoldenCoinsValue() {
        return goldenCoinsValue;
    }

    public void setGoldenCoinsValue(Integer goldenCoinsValue) {
        this.goldenCoinsValue = goldenCoinsValue;
    }


    public Integer getGametype() {
        return gametype;
    }

    public void setGametype(Integer gametype) {
        this.gametype = gametype;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", memberFkUserId=").append(memberFkUserId);
        sb.append(", goldenBeansBefore=").append(goldenBeansBefore);
        sb.append(", goldenCoinsBefore=").append(goldenCoinsBefore);
        sb.append(", goldenBeansAfter=").append(goldenBeansAfter);
        sb.append(", goldenCoinsAfter=").append(goldenCoinsAfter);
        sb.append(", gamebegintime=").append(gamebegintime);
        sb.append(", gameendtime=").append(gameendtime);
        sb.append(", iswin=").append(iswin);
        sb.append(", goldenBeansValue=").append(goldenBeansValue);
        sb.append(", goldenCoinsValue=").append(goldenCoinsValue);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}