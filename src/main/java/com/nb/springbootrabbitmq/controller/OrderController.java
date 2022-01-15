package com.nb.springbootrabbitmq.controller;

import com.nb.springbootrabbitmq.service.OrderService;
import com.nb.springbootrabbitmq.vo.OrderVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @Author: nb
 * @Date: 2021/3/20 21:01
 * @Version 1.0
 */

@RestController
@RequestMapping("/sendOrder")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/send")
    public void sendOrder(@Valid @RequestBody OrderVO orderVO) {
        orderService.makeOrder(orderVO);
    }

    @PostMapping("/sendDirect")
    public void sendDirectOrder(@Valid @RequestBody OrderVO orderVO){

        orderService.sendDirectOrder(orderVO);
    }

    @PostMapping("/sendTopic")
    public void sendTopicOrder(@Valid @RequestBody OrderVO orderVO){
        orderService.sendTopicOrder(orderVO);
    }

    @PostMapping("/sendTtl")
    public void sendTtl(@Valid @RequestBody OrderVO orderVO){
        orderService.sendTtlOrder(orderVO);
    }

    @PostMapping("/sendDead")
    public void sendDead(@Valid @RequestBody OrderVO orderVO){
        orderService.sendDeadOrder(orderVO);
    }

    @PostMapping("/sendTest")
    public void sendTest(@Valid @RequestBody OrderVO orderVO){
        orderService.sendTestOrder(orderVO);
    }


}
