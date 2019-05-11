package com.example.eurekaclient.pojo;

import java.io.Serializable;
import java.util.UUID;

/*
 * @Author Hman
 * @Description 商品 实体
 * @Date 2019/3/7 19:37
 * @Version  1.0
 **/
public class Goods implements Serializable {
    private static final long serialVersionUID = 1L;

    private String id;

    private String goodsName;

    private int price;

    /**
     *  商品简介
     */
    private String details;

    public Goods() {
    }

    public Goods(String id,String goodsName, int price, String details) {
        this.id =id;
        this.goodsName = goodsName;
        this.price = price;
        this.details = details;
    }

    @Override
    public String toString() {
        return "Goods{" +
                "id='" + id + '\'' +
                ", goodsName='" + goodsName + '\'' +
                ", price=" + price +
                ", details='" + details + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}
