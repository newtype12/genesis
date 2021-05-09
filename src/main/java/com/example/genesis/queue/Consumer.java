package com.example.genesis.queue;

import com.example.genesis.data.entity.Order;
import com.example.genesis.service.UserService;
import com.example.genesis.util.RedisSetUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.activemq.ActiveMQMessageConsumer;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import java.util.concurrent.CountDownLatch;

@Component
@Slf4j
@RequiredArgsConstructor
public class Consumer  {
    private final UserService userService;
    private final RedisSetUtil redisSetUtil;
    private CountDownLatch latch = new CountDownLatch(1);

    public CountDownLatch getLatch() {
        return latch;
    }

    @JmsListener(destination = "order-queue")
    public void onMessage(Message message) {
        try {
            // check user first
            Integer handlerId = redisSetUtil.getAvailableUserID();
            if (handlerId > 0) {
                ObjectMessage objectMessage = (ObjectMessage) message;
                Order order = (Order) objectMessage.getObject();
                //do additional processing
                log.info("user: {} handle Received Message: {}", handlerId, order.toString());
                latch.countDown();
            }
            log.info("all user are busy , wait !");

        } catch (Exception e) {
            log.error("Received Exception : " + e);
        }

    }
}
