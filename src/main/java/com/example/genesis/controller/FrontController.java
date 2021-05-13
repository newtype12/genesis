package com.example.genesis.controller;

import com.example.genesis.bo.OrderBo;
import com.example.genesis.queue.InternalOrderQueue;
import com.example.genesis.queue.Producer;
import com.example.genesis.service.OrderService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/front")
@Api(tags = "front")
@Slf4j
@RequiredArgsConstructor
public class FrontController {

    private final OrderService orderService;

    private final Producer producer;

    @PostMapping("order/create")
    public void getOrders(OrderBo bo) {
        orderService.createOrder(bo);
    }

    @GetMapping("order")
    public String showQueueSize() {
        return producer.checkQueue("order-queue");
    }

}
