package com.example.activemq.consumer;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

/*
 * @Author 点对点 消费者
 * @Description //TODO
 * @Date 2019/2/25 21:41
 * @Version  1.0
 **/
@Component
public class PTPConcumer {
    /**
     *  该注解为 监听名为 order.queue 的队列
     * @param msg order.queue 的信息
     */
    @JmsListener(destination = "order.queue")
    public void orderMsg(String msg) {
        System.out.println("order 接收到消息：" + msg);
    }

    @JmsListener(destination = "order.queue")
    public void orderMsg2(String msg) {
        System.out.println("order2 接收到消息：" + msg);
    }

    @JmsListener(destination = "common.queue")
    public void commonMsg(String msg) {
        System.out.println("common 接收到消息：" + msg);
    }

}
