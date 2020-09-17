package com.shengming.entity;

/**
 * @author XuChenFeng
 * @Date 2020/9/2 9:30
 */
public class MallGoodsDTO {
    //商品id
    private Integer goodsId;
    //商品名称
    private Integer goodsName;
    //商品单价
    private Integer unitprice;
    //商品类别，1=金币，2=金豆，3=福卡
    private Integer category;

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public Integer getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(Integer goodsName) {
        this.goodsName = goodsName;
    }

    public Integer getUnitprice() {
        return unitprice;
    }

    public void setUnitprice(Integer unitprice) {
        this.unitprice = unitprice;
    }

    public Integer getCategory() {
        return category;
    }

    public void setCategory(Integer category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "MallGoodsDTO{" +
                "goodsId=" + goodsId +
                ", goodsName=" + goodsName +
                ", unitprice=" + unitprice +
                ", category=" + category +
                '}';
    }
}
