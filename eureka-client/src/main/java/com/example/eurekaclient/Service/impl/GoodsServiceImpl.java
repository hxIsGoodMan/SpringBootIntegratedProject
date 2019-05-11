package com.example.eurekaclient.Service.impl;

import com.example.eurekaclient.Service.GoodsService;
import com.example.eurekaclient.pojo.Goods;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/*
 * @Author Hman
 * @Description //TODO
 * @Date 2019/3/7 19:47
 * @Version  1.0
 **/
@Service
public class GoodsServiceImpl implements GoodsService {

//    模拟数据
    private static List<Goods> goodes=new ArrayList<>();

    static {
       goodes.add(new Goods("1","笔记本",4999,"厉害厉害"));
        goodes.add(new Goods("2","苹果手机",8999,"牛皮牛皮"));
        goodes.add(new Goods("3","华为手机",4999,"666"));
        goodes.add(new Goods("4","魅族",4999,"棒棒棒"));
    }


    @Override
    public Goods getById(String id) {
        for (Goods g : goodes ) {
            if(g.getId().equals(id)){
                return g;
            }
        }
        return null;
    }

    @Override
    public List<Goods> findAll() {
        return goodes;
    }
}
