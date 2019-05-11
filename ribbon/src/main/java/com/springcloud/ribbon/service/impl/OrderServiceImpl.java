package com.springcloud.ribbon.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.springcloud.ribbon.domain.Order;
import com.springcloud.ribbon.feignclient.FeignClientDemo;
import com.springcloud.ribbon.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.TimeUnit;

/*
 * @Author Hman
 * @Description //TODO
 * @Date 2019/3/8 20:03
 * @Version  1.0
 **/
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private FeignClientDemo feignClientDemo;

    @Override
    public Order add(String goodsId, int num) {
        String goodsStr=feignClientDemo.getById(goodsId);
        //线程睡眠工具类
//        try {
//            TimeUnit.SECONDS.sleep(2);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }


        Map<String,String> map=JSONObject.parseObject(goodsStr, Map.class);

       Order order=new Order("1",map.get("id"),map.get("goodsName"),num,"9527");
        return order;
    }
}
