package com.shengming.entity;

import java.io.Serializable;
import java.util.Date;

public class MailDTO implements Serializable {

    private Integer mailId;

    private Integer mailType;

    private String mailName;

    private String mailContent;

    private Date mailSendtime;

    private Integer ifsee;

    private Integer gifttpye;

    private Integer giftnum;

    private Integer userid;

    private Integer friendid;

    private Integer ifSend;

    private static final long serialVersionUID = 1L;


    public Integer getMailId() {
        return mailId;
    }

    public void setMailId(Integer mailId) {
        this.mailId = mailId;
    }

    public Integer getMailType() {
        return mailType;
    }

    public void setMailType(Integer mailType) {
        this.mailType = mailType;
    }

    public String getMailName() {
        return mailName;
    }

    public void setMailName(String mailName) {
        this.mailName = mailName == null ? null : mailName.trim();
    }

    public String getMailContent() {
        return mailContent;
    }

    public void setMailContent(String mailContent) {
        this.mailContent = mailContent == null ? null : mailContent.trim();
    }

    public Date getMailSendtime() {
        return mailSendtime;
    }

    public void setMailSendtime(Date mailSendtime) {
        this.mailSendtime = mailSendtime;
    }

    public Integer getIfsee() {
        return ifsee;
    }

    public void setIfsee(Integer ifsee) {
        this.ifsee = ifsee;
    }

    public Integer getGifttpye() {
        return gifttpye;
    }

    public void setGifttpye(Integer gifttpye) {
        this.gifttpye = gifttpye;
    }

    public Integer getGiftnum() {
        return giftnum;
    }

    public void setGiftnum(Integer giftnum) {
        this.giftnum = giftnum;
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


    public Integer getIfSend() {
        return ifSend;
    }

    public void setIfSend(Integer ifSend) {
        this.ifSend = ifSend;
    }

    @Override
    public String toString() {
        return "MailDTO{" +
                "mailId=" + mailId +
                ", mailType=" + mailType +
                ", mailName='" + mailName + '\'' +
                ", mailContent='" + mailContent + '\'' +
                ", mailSendtime=" + mailSendtime +
                ", ifsee=" + ifsee +
                ", gifttpye=" + gifttpye +
                ", giftnum=" + giftnum +
                ", userid=" + userid +
                ", friendid=" + friendid +
                ", ifSend=" + ifSend +
                '}';
    }
}