package com.springcloud.ribbon.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.springcloud.ribbon.feignclient.FeignClientDemo;
import com.springcloud.ribbon.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/*
 * @Author Hman
 * @Description //TODO
 * @Date 2019/3/6 19:49
 * @Version  1.0
 **/
@RestController
@RequestMapping("feign")
public class FeignController {

    private final Logger log= LoggerFactory.getLogger(getClass());
    @Autowired
    private FeignClientDemo feignClientDemo;

    @Autowired
    private OrderService orderService;

    @Value("${feign.hystrix.enabled}")
    private String hystrixFlag;

    @RequestMapping("/findAll")
    @HystrixCommand(fallbackMethod = "fondAllFallBack")
    public String findAll(){
        System.out.println(hystrixFlag);
        return feignClientDemo.findAll();
    }

    @RequestMapping("/add")
    @HystrixCommand(fallbackMethod = "allFallBack")
    public Object add(@RequestParam("goodsId")String goodsId,@RequestParam("num")int  num){
        log.info("Feign add");
       return orderService.add(goodsId,num);
    }
    public String fondAllFallBack(){

        return "哈???";
    }

    public Object allFallBack(String goodsId,int  num){

        return "哈???idididid  "+goodsId;
    }
}
