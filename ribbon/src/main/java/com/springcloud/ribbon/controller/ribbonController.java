package com.springcloud.ribbon.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/*
 * @Author Hman
 * @Description 测试Ribbon 调用其他client 端 接口
 * @Date 2019/3/5 20:42
 * @Version  1.0
 **/
@RestController
@RequestMapping("ribbon/")
@EnableHystrix
public class ribbonController {

    @Autowired
    private RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod="findALLFallbackMethod")
    @RequestMapping("findAll")
    public Object findAll(){

        return restTemplate.getForEntity("http://goods/goods/findAll",Object.class);
    }

    public Object findALLFallbackMethod(){
        return "出!错!啦! 豪猪出动~~";
    }
}
