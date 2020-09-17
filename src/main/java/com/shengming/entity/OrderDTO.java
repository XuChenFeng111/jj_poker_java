package com.shengming.entity;

import java.io.Serializable;
import java.util.Date;

public class OrderDTO implements Serializable {
    private Integer id;

    private String orderId;

    private Integer memberFkUserId;

    private Integer mallFkGoodsId;

    private Integer memberFkRecommenderId;

    private Date buytime;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Integer getMemberFkUserId() {
        return memberFkUserId;
    }

    public void setMemberFkUserId(Integer memberFkUserId) {
        this.memberFkUserId = memberFkUserId;
    }

    public Integer getMallFkGoodsId() {
        return mallFkGoodsId;
    }

    public void setMallFkGoodsId(Integer mallFkGoodsId) {
        this.mallFkGoodsId = mallFkGoodsId;
    }

    public Integer getMemberFkRecommenderId() {
        return memberFkRecommenderId;
    }

    public void setMemberFkRecommenderId(Integer memberFkRecommenderId) {
        this.memberFkRecommenderId = memberFkRecommenderId;
    }

    public Date getBuytime() {
        return buytime;
    }

    public void setBuytime(Date buytime) {
        this.buytime = buytime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", orderId=").append(orderId);
        sb.append(", memberFkUserId=").append(memberFkUserId);
        sb.append(", mallFkGoodsId=").append(mallFkGoodsId);
        sb.append(", memberFkRecommenderId=").append(memberFkRecommenderId);
        sb.append(", buytime=").append(buytime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}