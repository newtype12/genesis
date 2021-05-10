package com.example.genesis.queue;

import lombok.RequiredArgsConstructor;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.jms.Destination;

@Service
@RequiredArgsConstructor
public class Producer {
    private final InternalOrderQueue internalOrderQueue;

    public void convertAndSend(Object message) {
        internalOrderQueue.add(message);
    }
}
