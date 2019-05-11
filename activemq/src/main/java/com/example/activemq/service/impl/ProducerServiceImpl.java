package com.example.activemq.service.impl;


import com.example.activemq.service.ProducerService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import javax.jms.Destination;
import javax.jms.Queue;
import javax.jms.Topic;

import org.springframework.jms.core.JmsMessagingTemplate;

/*
 * @Author Hman
 * @Description //TODO
 * @Date 2019/2/25 20:39
 * @Version  1.0
 **/
@Service
public class ProducerServiceImpl implements ProducerService {

    @Autowired
    private JmsMessagingTemplate jmsTemplate; //jms 操作模版 封装了一些日常操作

    @Autowired
    private Queue queue; // 入口创建的 公共队列
    /**
     *  指定消息队列 发送消息
     * @param destination 指定的队列
     * @param message 要发送的消息
     */
    @Override
   public void senMessage(Destination destination, final String message){
        jmsTemplate.convertAndSend(destination,message);
    }

    /**
     *      往默认(公共)的消息队列 发送消息
     * @param message 要发送的消息
     */
    @Override
    public void senMessage(final String message){
        jmsTemplate.convertAndSend(this.queue,message);
    }


    //注入 入口定义的 发布订阅 队列 对象
    @Autowired
    private Topic topic;
    /**
     *  发布订阅模式 的 发送消息 方法
     * @param msg
     */
    @Override
    public void publish(String msg) {
        jmsTemplate.convertAndSend(this.topic,msg);
    }
    @Override
    public void publish(Destination destination,String msg){
        jmsTemplate.convertAndSend(destination,msg);
    }
}
