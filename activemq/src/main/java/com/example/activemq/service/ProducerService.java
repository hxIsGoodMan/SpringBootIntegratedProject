package com.example.activemq.service;


import org.springframework.stereotype.Service;

import javax.jms.Destination;

/**
 * @Author:生产者 业务层接口
 * @Date: 2019/2/25  20:36
 * @Description : 定义消息队列发送消息的方法
 * @Version: 1.0
 */

public interface ProducerService {

    /**
     *  指定消息队列 发送消息
     * @param destination 指定的队列
     * @param message 要发送的消息
     */
    void senMessage(Destination destination, final String message);

    /**
     *      往默认(公共)的消息队列 发送消息
     * @param message 要发送的消息
     */
    void senMessage(final String message);

    /**
     *      向公共 发布/订阅 队列 发布msg
     * @param msg
     */
    void publish(String msg);

    /**
     *      向公共 发布/订阅 队列 发布msg
     * @param destination 传入的队列
     */
    void publish(Destination destination,String msg);
}
