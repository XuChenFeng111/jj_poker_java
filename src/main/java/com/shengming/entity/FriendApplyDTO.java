package com.shengming.entity;

import java.util.Date;

/**
 * @author XuChenFeng
 * @Date 2020/8/29 13:49
 */
public class FriendApplyDTO {

    private Integer userId;
    private String avatar;
    private String nickName;
    private Integer level;
    private Integer friendId;
    private Date applyTime;
    private Integer applyStatus;
    private String applyDiffTime;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getFriendId() {
        return friendId;
    }

    public void setFriendId(Integer friendId) {
        this.friendId = friendId;
    }

    public Date getApplyTime() {
        return applyTime;
    }

    public void setApplyTime(Date applyTime) {
        this.applyTime = applyTime;
    }

    public Integer getApplyStatus() {
        return applyStatus;
    }

    public void setApplyStatus(Integer applyStatus) {
        this.applyStatus = applyStatus;
    }

    public String getApplyDiffTime() {
        return applyDiffTime;
    }

    public void setApplyDiffTime(String applyDiffTime) {
        this.applyDiffTime = applyDiffTime;
    }

    @Override
    public String toString() {
        return "FriendApplyDTO{" +
                "userId=" + userId +
                ", avatar='" + avatar + '\'' +
                ", nickName='" + nickName + '\'' +
                ", level=" + level +
                ", friendId=" + friendId +
                ", applyTime='" + applyTime + '\'' +
                ", applyStatus=" + applyStatus +
                '}';
    }
}
