package com.shengming.entity;

import java.util.Date;

/**
 * @author XuChenFeng
 * @Date 2020/9/2 16:44
 */
public class MailDetailsDTO {
    private Integer mailId;
    private Integer userid;
    private Integer friendid;
    private String friendAvatar;

    private String friendNickName;

    private Date mailSendtime;

    private String diffTime;

    public Integer getMailId() {
        return mailId;
    }

    public void setMailId(Integer mailId) {
        this.mailId = mailId;
    }

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

    public String getFriendAvatar() {
        return friendAvatar;
    }

    public void setFriendAvatar(String friendAvatar) {
        this.friendAvatar = friendAvatar;
    }

    public String getFriendNickName() {
        return friendNickName;
    }

    public void setFriendNickName(String friendNickName) {
        this.friendNickName = friendNickName;
    }

    public String getDiffTime() {
        return diffTime;
    }

    public void setDiffTime(String diffTime) {
        this.diffTime = diffTime;
    }

    public Date getMailSendtime() {
        return mailSendtime;
    }

    public void setMailSendtime(Date mailSendtime) {
        this.mailSendtime = mailSendtime;
    }
}
