package com.example.genesis.service;

import com.example.genesis.bo.OrderBo;
import com.example.genesis.data.entity.Order;
import com.example.genesis.data.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    public void createOrder(OrderBo bo) {
        int result=orderRepository.insertOrder(bo.getContent(), bo.getName(), bo.getTitle());
        if(result>0){
            //todo 塞mq
        }


    }
}
