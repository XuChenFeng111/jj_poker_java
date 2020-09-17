package com.shengming.entity;

/**
 * @author XuChenFeng
 * @Date 2020/8/24 14:00
 */
public class UserDetailsDTO {
    private Integer userid;
    private String username;
    private String nickname;
    private String avatar;
    private Integer level;
    private Integer point;
    private Integer goldcoin;
    private Integer fukaNum;
    private Integer gender;
    private Integer isrn;
    private Integer recommenderId;
    private Integer recomNum;
    private Integer isvip;
    private Integer ifphover;
    private Integer amount;
    private Integer oftengame;

    public Integer getOftengame() {
        return oftengame;
    }

    public void setOftengame(Integer oftengame) {
        this.oftengame = oftengame;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getPoint() {
        return point;
    }

    public void setPoint(Integer point) {
        this.point = point;
    }

    public Integer getGoldcoin() {
        return goldcoin;
    }

    public void setGoldcoin(Integer goldcoin) {
        this.goldcoin = goldcoin;
    }

    public Integer getFukaNum() {
        return fukaNum;
    }

    public void setFukaNum(Integer fukaNum) {
        this.fukaNum = fukaNum;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public Integer getIsrn() {
        return isrn;
    }

    public void setIsrn(Integer isrn) {
        this.isrn = isrn;
    }

    public Integer getRecommenderId() {
        return recommenderId;
    }

    public void setRecommenderId(Integer recommenderId) {
        this.recommenderId = recommenderId;
    }

    public Integer getRecomNum() {
        return recomNum;
    }

    public void setRecomNum(Integer recomNum) {
        this.recomNum = recomNum;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }


    public Integer getIsvip() {
        return isvip;
    }

    public void setIsvip(Integer isvip) {
        this.isvip = isvip;
    }

    public Integer getIfphover() {
        return ifphover;
    }

    public void setIfphover(Integer ifphover) {
        this.ifphover = ifphover;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "UserDetailsDTO{" +
                "userid=" + userid +
                ", username='" + username + '\'' +
                ", nickname='" + nickname + '\'' +
                ", avatar='" + avatar + '\'' +
                ", level=" + level +
                ", point=" + point +
                ", goldcoin=" + goldcoin +
                ", fukaNum=" + fukaNum +
                ", gender=" + gender +
                ", isrn=" + isrn +
                ", recommenderId=" + recommenderId +
                ", recomNum=" + recomNum +
                ", isvip=" + isvip +
                ", ifphover=" + ifphover +
                ", amount=" + amount +
                '}';
    }
}
