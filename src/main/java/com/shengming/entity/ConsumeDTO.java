package com.shengming.entity;

import java.io.Serializable;

public class ConsumeDTO implements Serializable {
    private Integer id;

    private Integer convertid;

    private String convertgift;

    private Integer convertConsume;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getConvertid() {
        return convertid;
    }

    public void setConvertid(Integer convertid) {
        this.convertid = convertid;
    }

    public String getConvertgift() {
        return convertgift;
    }

    public void setConvertgift(String convertgift) {
        this.convertgift = convertgift == null ? null : convertgift.trim();
    }

    public Integer getConvertConsume() {
        return convertConsume;
    }

    public void setConvertConsume(Integer convertConsume) {
        this.convertConsume = convertConsume;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", convertid=").append(convertid);
        sb.append(", convertgift=").append(convertgift);
        sb.append(", convertConsume=").append(convertConsume);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}