package com.example.genesis.queue;

import com.example.genesis.data.entity.Order;
import com.example.genesis.data.repository.OrderRepository;
import com.example.genesis.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.jms.Destination;
import java.util.List;

@Service
@RequiredArgsConstructor
public class Producer {
    private final JmsTemplate jmsTemplate;
    private final static Destination destination = new ActiveMQQueue("order-queue");

    @Autowired
    private OrderRepository orderRepository;

    @Scheduled(initialDelay = 10000, fixedDelay = 500000)
    public void getUndoneMessage() {
        List<Order> orders = this.getUndone();
        if (orders.size() > 0) {
            for (Order o : orders) {
                convertAndSend(o);
            }
        }
    }

    public void convertAndSend(Object message) {

        jmsTemplate.convertAndSend(destination, message);
    }

    public List<Order> getUndone() {
        return orderRepository.getUndoneOrder(0);
    }

}
