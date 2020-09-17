package com.shengming.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class UserDTO implements Serializable {
    private Integer userid;

    private String username;

    private String password;

    private String encrypt;

    private String nickname;

    private String avatar;

    private Date regtime;

    private Date lasttime;

    private String regip;

    private String lastip;

    private Integer loginnum;

    private String email;

    private Integer groupid;

    private String address;

    private Integer amount;

    private Integer point;

    private Integer goldcoin;

    private Integer islock;

    private String qq;

    private String mobile;

    private String weixin;

    private Integer isrn;

    private String realname;

    private String idCard;

    private Integer isvip;

    private String openid;

    private String accessToken;

    private String accessExpiretoken;

    private String refreshToken;

    private Integer recommenderId;

    private Integer gender;

    private Integer level;

    private Integer fukanum;

    private String token;

    private Integer logonType;

    private String remarks;

    private Integer ifphover;

    private static final long serialVersionUID = 1L;

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
        this.username = username == null ? null : username.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getEncrypt() {
        return encrypt;
    }

    public void setEncrypt(String encrypt) {
        this.encrypt = encrypt == null ? null : encrypt.trim();
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname == null ? null : nickname.trim();
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar == null ? null : avatar.trim();
    }

    public Date getRegtime() {
        return regtime;
    }

    public void setRegtime(Date regtime) {
        this.regtime = regtime;
    }

    public Date getLasttime() {
        return lasttime;
    }

    public void setLasttime(Date lasttime) {
        this.lasttime = lasttime;
    }

    public String getRegip() {
        return regip;
    }

    public void setRegip(String regip) {
        this.regip = regip == null ? null : regip.trim();
    }

    public String getLastip() {
        return lastip;
    }

    public void setLastip(String lastip) {
        this.lastip = lastip == null ? null : lastip.trim();
    }

    public Integer getLoginnum() {
        return loginnum;
    }

    public void setLoginnum(Integer loginnum) {
        this.loginnum = loginnum;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public Integer getGroupid() {
        return groupid;
    }

    public void setGroupid(Integer groupid) {
        this.groupid = groupid;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
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

    public Integer getIfphover() {
        return ifphover;
    }

    public void setIfphover(Integer ifphover) {
        this.ifphover = ifphover;
    }

    public void setGoldcoin(Integer goldcoin) {
        this.goldcoin = goldcoin;
    }

    public Integer getIslock() {
        return islock;
    }

    public void setIslock(Integer islock) {
        this.islock = islock;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq == null ? null : qq.trim();
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    public String getWeixin() {
        return weixin;
    }

    public void setWeixin(String weixin) {
        this.weixin = weixin == null ? null : weixin.trim();
    }

    public Integer getIsrn() {
        return isrn;
    }

    public void setIsrn(Integer isrn) {
        this.isrn = isrn;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname == null ? null : realname.trim();
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard == null ? null : idCard.trim();
    }

    public Integer getIsvip() {
        return isvip;
    }

    public void setIsvip(Integer isvip) {
        this.isvip = isvip;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid == null ? null : openid.trim();
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken == null ? null : accessToken.trim();
    }

    public String getAccessExpiretoken() {
        return accessExpiretoken;
    }

    public void setAccessExpiretoken(String accessExpiretoken) {
        this.accessExpiretoken = accessExpiretoken == null ? null : accessExpiretoken.trim();
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken == null ? null : refreshToken.trim();
    }

    public Integer getRecommenderId() {
        return recommenderId;
    }

    public void setRecommenderId(Integer recommenderId) {
        this.recommenderId = recommenderId;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getFukanum() {
        return fukanum;
    }

    public void setFukanum(Integer fukanum) {
        this.fukanum = fukanum;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token == null ? null : token.trim();
    }

    public Integer getLogonType() {
        return logonType;
    }

    public void setLogonType(Integer logonType) {
        this.logonType = logonType;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", userid=").append(userid);
        sb.append(", username=").append(username);
        sb.append(", password=").append(password);
        sb.append(", encrypt=").append(encrypt);
        sb.append(", nickname=").append(nickname);
        sb.append(", avatar=").append(avatar);
        sb.append(", regtime=").append(regtime);
        sb.append(", lasttime=").append(lasttime);
        sb.append(", regip=").append(regip);
        sb.append(", lastip=").append(lastip);
        sb.append(", loginnum=").append(loginnum);
        sb.append(", email=").append(email);
        sb.append(", groupid=").append(groupid);
        sb.append(", address=").append(address);
        sb.append(", amount=").append(amount);
        sb.append(", point=").append(point);
        sb.append(", goldcoin=").append(goldcoin);
        sb.append(", islock=").append(islock);
        sb.append(", qq=").append(qq);
        sb.append(", mobile=").append(mobile);
        sb.append(", weixin=").append(weixin);
        sb.append(", isrn=").append(isrn);
        sb.append(", realname=").append(realname);
        sb.append(", idCard=").append(idCard);
        sb.append(", isvip=").append(isvip);
        sb.append(", openid=").append(openid);
        sb.append(", accessToken=").append(accessToken);
        sb.append(", accessExpiretoken=").append(accessExpiretoken);
        sb.append(", refreshToken=").append(refreshToken);
        sb.append(", recommenderId=").append(recommenderId);
        sb.append(", gender=").append(gender);
        sb.append(", level=").append(level);
        sb.append(", fukanum=").append(fukanum);
        sb.append(", token=").append(token);
        sb.append(", logontype=").append(logonType);
        sb.append(", remarks=").append(remarks);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }


}