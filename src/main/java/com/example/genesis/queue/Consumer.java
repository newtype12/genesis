package com.example.genesis.queue;

import com.example.genesis.data.entity.Order;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

@Component
@Slf4j
public class Consumer implements MessageListener {


    @Override
    @JmsListener(destination = "order-queue" )
    public void onMessage(Message message) {
        try{
            ObjectMessage objectMessage = (ObjectMessage)message;
            Order order = (Order)objectMessage.getObject();
            //do additional processing
            log.info("Received Message: "+ order.toString());
        } catch(Exception e) {
            log.error("Received Exception : "+ e);
        }

    }
}
