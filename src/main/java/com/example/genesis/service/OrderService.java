package com.example.genesis.service;

import com.example.genesis.bo.OrderBo;
import com.example.genesis.data.entity.Order;
import com.example.genesis.data.repository.OrderRepository;
import com.example.genesis.queue.Producer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    private final Producer producer;

    public void createOrder(OrderBo bo) {
//        int result=orderRepository.insertOrder(bo.getContent(), bo.getName(), bo.getTitle());

        Order result=orderRepository.save(new Order(null,bo.getName(), bo.getTitle(),bo.getContent(),0,null,0 ));
        if(result!=null){
            producer.convertAndSend(result);
        }
    }
}
