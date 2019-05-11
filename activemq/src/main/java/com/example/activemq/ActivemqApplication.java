package com.example.activemq;

import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;

import javax.jms.ConnectionFactory;
import javax.jms.Queue;
import javax.jms.Topic;

/**
  * @Description:     SpringBoot 入口
  * @Author:         Hman
  * @CreateDate:    2019/2/25 20:30
  * @Version:        1.0
 */
@SpringBootApplication
@EnableJms //开启jms 注解
public class ActivemqApplication {

    /**
     *  默认创建一个公共的队列 Queue : 点对点
     * @return 一个点对点队列
     */
    @Bean
    public Queue queer(){
        return new ActiveMQQueue("common.queue");
    }

    /**
     *  默认创建一个公共的队列 Topic : 发布/订阅
     * @return 一个发布/订阅队列
     */
    @Bean
    public Topic topic(){
        return new ActiveMQTopic("common.topic");
    }

    /**
     *      为了同时支持 点对点 以及 发布订阅两种模式 需要重写  JmsListenerContainerFactory 这个类
     * @param activeMQConnectionFactory
     * @return
     */
    @Bean
    public JmsListenerContainerFactory<?> jmsListenerContainerTopic(ConnectionFactory activeMQConnectionFactory) {
        DefaultJmsListenerContainerFactory bean = new DefaultJmsListenerContainerFactory();
        bean.setPubSubDomain(true); //表示支持 发布/订阅
        bean.setConnectionFactory(activeMQConnectionFactory); //支持 点对点
        return bean;
    }
    public static void main(String[] args) {
        SpringApplication.run(ActivemqApplication.class, args);
    }

}
