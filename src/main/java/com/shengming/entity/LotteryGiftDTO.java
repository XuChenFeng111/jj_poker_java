package com.shengming.entity;

/**
 * @author XuChenFeng
 * @Date 2020/9/3 9:33
 */
public class LotteryGiftDTO {
    /**
     * 索引
     */
    private int id;
    /**
     * 奖品编号
     */
    private String gitfId;
    /**
     * 奖品名称
     */
    private String giftName;
    /**
     * 中奖概率
     */
    private double probability;

    public LotteryGiftDTO(int id, String gitfId, String giftName, double probability) {
        this.id = id;
        this.gitfId = gitfId;
        this.giftName = giftName;
        this.probability = probability;
    }

    public LotteryGiftDTO(){
        super();
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGitfId() {
        return gitfId;
    }

    public void setGitfId(String gitfId) {
        this.gitfId = gitfId;
    }

    public String getGiftName() {
        return giftName;
    }

    public void setGiftName(String giftName) {
        this.giftName = giftName;
    }

    public double getProbability() {
        return probability;
    }

    public void setProbability(double probability) {
        this.probability = probability;
    }

    @Override
    public String toString() {
        return "LotteryGiftDTO{" +
                "id=" + id +
                ", gitfId='" + gitfId + '\'' +
                ", giftName='" + giftName + '\'' +
                ", probability=" + probability +
                '}';
    }
}
