package com.example.activemq.consumer;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

/*
 * @Author Hman
 * @Description 发布订阅模式的 订阅者
 * @Date 2019/2/25 22:09
 * @Version  1.0
 **/
@Component
public class Subscribe {

    /**
     *  该注解为 监听名为 commmon.topic 的队列
     * @param msg commmon.topic 的信息
     *            containerFactory = "jmsListenerContainerTopic" 表示 使用 我们重写后的 topic
     */
    @JmsListener(destination = "common.topic",containerFactory = "jmsListenerContainerTopic")
    public void subscribe1(String msg) {
        System.out.println("subscribe1 订阅者1 接收到消息：" + msg);
    }

    @JmsListener(destination = "common.topic",containerFactory = "jmsListenerContainerTopic")
    public void subscribe2(String msg) {
        System.out.println("subscribe2 订阅者2 接收到消息：" + msg);
    }

    @JmsListener(destination = "idol.topic")
    public void idol(String msg) {
        System.out.println("idol 订阅者3 接收到消息：" + msg);
    }

}
