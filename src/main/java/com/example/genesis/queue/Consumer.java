package com.example.genesis.queue;

import com.example.genesis.data.entity.Order;
import com.example.genesis.service.UserService;
import com.example.genesis.util.RedisSetUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import java.util.concurrent.CountDownLatch;

@Component
@Slf4j
@RequiredArgsConstructor
public class Consumer{
    private final UserService userService;
    private final RedisSetUtil redisSetUtil;
    private final JmsTemplate jmsTemplate;
    private CountDownLatch latch = new CountDownLatch(1);

    @Scheduled(initialDelay = 1000, fixedDelay = 2000)
    public void onMessage() {
        try {
           // check user first
            Integer handlerId = redisSetUtil.getAvailableUserID();
            if (handlerId > 0) {
                ObjectMessage objectMessage = (ObjectMessage) jmsTemplate.receive("order-queue");
                Order order = (Order) objectMessage.getObject();
                log.info("user: {} handle Received Message: {}", handlerId, order.toString());
                latch.countDown();
            }

        } catch (Exception e) {
            log.error("Received Exception : " + e);
        }

    }
}
