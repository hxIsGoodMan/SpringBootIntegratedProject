package com.springcloud.ribbon.domain;

import java.io.Serializable;

/*
 * @Author Hman
 * @Description //TODO
 * @Date 2019/3/8 19:55
 * @Version  1.0
 **/
public class Order implements Serializable {
    private static final long serialVersionUID = 3536579538308054257L;

    private String id;

    private String goodsId;
    private String goodsName;
    /**
     *  购买数量
     */
    private int num;

    private String userId;

    public Order() {

    }

    @Override
    public String toString() {
        return "Order{" +
                "id='" + id + '\'' +
                ", goodsId='" + goodsId + '\'' +
                ", goodsName='" + goodsName + '\'' +
                ", num=" + num +
                ", userId='" + userId + '\'' +
                '}';
    }

    public Order(String id, String goodsId, String goodsName, int num, String userId) {
        this.id = id;
        this.goodsId = goodsId;
        this.goodsName = goodsName;
        this.num = num;
        this.userId = userId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
