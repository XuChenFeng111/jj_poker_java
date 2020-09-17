package com.shengming.entity;


/**
 * @author XuChenFeng
 * @Date 2020/9/10 10:18
 */
public class GamePlayerDTO {

    private String userids;

    private Integer userid;
    //是否加倍
    private Integer ifDouble;
    //是否是地主
    private Integer isLandowner;
    //是否抢地主
    private Integer ifGrab;

    private Integer gametype;

    public String getUserids() {
        return userids;
    }

    public void setUserids(String userids) {
        this.userids = userids;
    }

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

    public Integer getIfDouble() {
        return ifDouble;
    }

    public void setIfDouble(Integer ifDouble) {
        this.ifDouble = ifDouble;
    }

    public Integer getIsLandowner() {
        return isLandowner;
    }

    public void setIsLandowner(Integer isLandowner) {
        this.isLandowner = isLandowner;
    }

    public Integer getIfGrab() {
        return ifGrab;
    }

    public void setIfGrab(Integer ifGrab) {
        this.ifGrab = ifGrab;
    }
}
