package com.example.genesis.service;

import com.example.genesis.bo.OrderBo;
import com.example.genesis.data.entity.Order;
import com.example.genesis.data.repository.OrderRepository;
import com.example.genesis.queue.Producer;
import lombok.RequiredArgsConstructor;
import org.aspectj.weaver.ast.Or;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import java.util.Enumeration;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    private final Producer producer;
    private final JmsTemplate jmsTemplate;

    public void createOrder(OrderBo bo) {
        Order result = orderRepository.save(new Order(null, bo.getName(), bo.getTitle(), bo.getContent(), 0, null, 0));
        if (result != null) {
            producer.convertAndSend(result);
        }
    }
    public String checkQueue(String queue) {
        return jmsTemplate.browse(queue, (session, browser) -> {
            Enumeration<?> messages = browser.getEnumeration();
            int total = 0;
            while (messages.hasMoreElements()) {
                messages.nextElement();
                total++;
            }
            return String.format("Total '%d elements waiting in %s", total, queue);
        });
    }
}
