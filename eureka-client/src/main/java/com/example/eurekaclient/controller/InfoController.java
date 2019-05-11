package com.example.eurekaclient.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/*
 * @Author Hman
 * @Description //TODO
 * @Date 2019/3/4 22:18
 * @Version  1.0
 **/
@Controller
@RequestMapping("/actuator")
public class InfoController {

    @RequestMapping("/info")
    @ResponseBody
    public String info(){
        return "这是测试SpringCloud Eureka Client 的测试类!! ";
    }
}
