package com.example.genesis.queue;

import com.example.genesis.data.entity.Order;
import com.example.genesis.data.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.jms.Destination;
import java.util.Enumeration;
import java.util.List;

@Service
@RequiredArgsConstructor
public class Producer {
    private final JmsTemplate jmsTemplate;
    private final static String queueName = "order-queue";
    private final static Destination destination = new ActiveMQQueue(queueName);

    @Autowired
    private OrderRepository orderRepository;

    //開啟服務把db未處裡的訂單撈到queue
    @PostConstruct
    public void getUndoneMessage() {
        List<Order> orders = this.getUndone();
        if (orders.size() > 0) {
            for (Order o : orders) {
                convertAndSend(o);
            }
            System.out.println(this.checkQueue(queueName));
        }
    }

    public void convertAndSend(Object message) {
        jmsTemplate.convertAndSend(destination, message);
    }

    public List<Order> getUndone() {
        return orderRepository.getUndoneOrder(0);
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
