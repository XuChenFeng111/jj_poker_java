package com.shengming.entity;

import java.io.Serializable;
import java.util.Date;

public class Attendance implements Serializable {
    private Integer id;

    private Integer memberFkUserId;

    private Date signtime;

    private Integer continuousdays;

    private String prize;

    private String monday;

    private String sunday;

    private String signdate;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMemberFkUserId() {
        return memberFkUserId;
    }

    public void setMemberFkUserId(Integer memberFkUserId) {
        this.memberFkUserId = memberFkUserId;
    }

    public Date getSigntime() {
        return signtime;
    }

    public void setSigntime(Date signtime) {
        this.signtime = signtime;
    }

    public Integer getContinuousdays() {
        return continuousdays;
    }

    public void setContinuousdays(Integer continuousdays) {
        this.continuousdays = continuousdays;
    }

    public String getPrize() {
        return prize;
    }

    public String getMonday() {
        return monday;
    }

    public void setMonday(String monday) {
        this.monday = monday;
    }

    public String getSunday() {
        return sunday;
    }

    public void setSunday(String sunday) {
        this.sunday = sunday;
    }

    public String getSigndate() {
        return signdate;
    }

    public void setSigndate(String signdate) {
        this.signdate = signdate;
    }

    public void setPrize(String prize) {
        this.prize = prize == null ? null : prize.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", memberFkUserId=").append(memberFkUserId);
        sb.append(", signtime=").append(signtime);
        sb.append(", continuousdays=").append(continuousdays);
        sb.append(", prize=").append(prize);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}