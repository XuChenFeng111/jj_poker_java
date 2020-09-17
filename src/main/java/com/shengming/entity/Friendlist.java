package com.shengming.entity;

import java.io.Serializable;
import java.util.Date;

public class Friendlist implements Serializable {

    private Integer userid;

    private Integer friendid;

    private Date applytime;

    private Integer applystatus;

    private Integer friendfrom;


    private static final long serialVersionUID = 1L;

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Integer getFriendid() {
        return friendid;
    }

    public void setFriendid(Integer friendid) {
        this.friendid = friendid;
    }

    public Date getApplytime() {
        return applytime;
    }

    public void setApplytime(Date applytime) {
        this.applytime = applytime;
    }

    public Integer getApplystatus() {
        return applystatus;
    }

    public void setApplystatus(Integer applystatus) {
        this.applystatus = applystatus;
    }

    public Integer getFriendfrom() {
        return friendfrom;
    }

    public void setFriendfrom(Integer friendfrom) {
        this.friendfrom = friendfrom;
    }

    @Override
    public String toString() {
        return "Friendlist{" +
                "userid=" + userid +
                ", friendid=" + friendid +
                ", applytime=" + applytime +
                ", applystatus=" + applystatus +
                ", friendfrom=" + friendfrom +
                '}';
    }
}