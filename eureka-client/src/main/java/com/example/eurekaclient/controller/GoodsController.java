package com.example.eurekaclient.controller;

import com.example.eurekaclient.Service.GoodsService;
import com.example.eurekaclient.pojo.Goods;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/*
 * @Author Hman
 * @Description //TODO
 * @Date 2019/3/7 19:55
 * @Version  1.0
 **/
@RestController
@RequestMapping("/goods")
public class GoodsController {
    private final Logger log= LoggerFactory.getLogger(getClass());
    @Autowired
    private GoodsService goodsService;

    @RequestMapping("/getById/{id}")
    public Object getById(@PathVariable("id") String id){
        log.info("goods getById");
        Map<String,Object> map=new HashMap<>();
        Goods goods=goodsService.getById(id);
        map.put("code","0000");
        map.put("data",goods);
        return goods;
    }
    @RequestMapping("/findAll")
    public Object findAll(){
        Map<String,Object> map=new HashMap<>();
        map.put("code","0000");
        map.put("data",goodsService.findAll());
        return map;
    }
}
