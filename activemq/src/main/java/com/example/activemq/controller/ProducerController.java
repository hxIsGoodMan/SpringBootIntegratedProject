package com.example.activemq.controller;

import com.example.activemq.service.ProducerService;
import com.example.activemq.service.impl.ProducerServiceImpl;
import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.jms.Destination;

/*
 * @Author Hman
 * @Description //TODO
 * @Date 2019/2/25 20:47
 * @Version  1.0
 **/
@Controller
@RequestMapping("activemq")
public class ProducerController {

    @Autowired
    private ProducerService producerService;

    /**
     *      模拟传送订单号 --> 创建新的订单队列
     * @param msg   订单号
     * @return
     */
    @GetMapping("order")
    @ResponseBody
    public String order(String msg){

        Destination destination = new ActiveMQQueue("order.queue");
        System.out.println("传送过来的 order msg 为: "+ msg);
        producerService.senMessage(destination,msg);
        return "order 发送消息-成功啦!! :"+msg;
    }

    /**
     *      向公共队列发送 msg
     * @param msg   msg
     * @return
     */
    @GetMapping("common")
    @ResponseBody
    public String common(String msg){
        System.out.println("传送过来的 common msg 为: "+ msg);
        producerService.senMessage(msg);
        return "common 发送消息-成功啦!! :"+msg;
    }

    @GetMapping("idol")
    @ResponseBody
    public String idol(String msg){
        System.out.println("传送过来的 idol msg 为: "+ msg);
        Destination destination = new ActiveMQTopic("idol.topic");
        producerService.publish(destination,msg);
        return "idol 发送消息-成功啦!! :"+msg;
    }

    /**
     *      向发布订阅 队列发送 msg
     * @param msg   msg
     * @return
     */
    @GetMapping("topic")
    @ResponseBody
    public String topic(String msg){
        System.out.println("传送过来的 topic msg 为: "+ msg);
        producerService.publish(msg);
        return "topic 发送消息-成功啦!! :"+msg;
    }
}
