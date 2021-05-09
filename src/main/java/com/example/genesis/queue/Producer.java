package com.example.genesis.queue;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.jms.Destination;

@Service
public class Producer {
    @Resource
    private JmsTemplate jmsTemplate;
    /**
     * 傳送訊息（主方法設定佇列）
     *
     * @param destination 傳送到的佇列
     * @param message     待發送的訊息
     */
    public void convertAndSend(Destination destination, final Object message) {
        jmsTemplate.convertAndSend(destination, message);
    }

    /**
     * 傳送訊息（定時傳送佇列）
     */
    static int i = 100;
    Destination destination = new ActiveMQQueue("order-queue");

    @Scheduled(fixedDelay = 3000) // 5s執行一次   只有無參的方法才能用該註解
    public void convertAndSend(Object message) {
        jmsTemplate.convertAndSend(destination, message);
    }

}
