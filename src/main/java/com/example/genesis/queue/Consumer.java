package com.example.genesis.queue;

import com.example.genesis.data.entity.Order;
import com.example.genesis.service.OrderService;
import com.example.genesis.service.UserService;
import com.example.genesis.util.RedisSetUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.jms.ObjectMessage;
import java.util.concurrent.CountDownLatch;

@Component
@Slf4j
@RequiredArgsConstructor
public class Consumer {
    private final UserService userService;
    private final OrderService orderService;
    private final RedisSetUtil redisSetUtil;
    private final JmsTemplate jmsTemplate;
    private final CountDownLatch latch = new CountDownLatch(1);


    /**
     * 當redis找到有空的使用者
     */
    @Scheduled(initialDelay = 1000, fixedDelay = 1000)
    public void onMessage() {
        try {
            // check user first
            Integer handlerId = userService.getAvailableUserID();
            if (handlerId > 0) {
                ObjectMessage objectMessage = (ObjectMessage) jmsTemplate.receive("order-queue");
                Order order = (Order) objectMessage.getObject();
                if (order.getStatus() == 0) {
                    log.info("user: {} handle Received Message: {}", handlerId, order.toString());
                    userService.distributeOrder(handlerId, order.getId());
                    orderService.updateOrderStatus(order.getId(), 1, handlerId);
                }
                latch.countDown();
            }

        } catch (Exception e) {
            log.error("Received Exception : " + e);
        }

    }
}
