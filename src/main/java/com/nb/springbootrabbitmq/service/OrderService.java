package com.nb.springbootrabbitmq.service;

import com.alibaba.fastjson.JSON;
import com.nb.springbootrabbitmq.dataobject.Order;
import com.nb.springbootrabbitmq.producer.callBack.ConfirmCallBack;
import com.nb.springbootrabbitmq.producer.callBack.ReturnCallBack;
import com.nb.springbootrabbitmq.producer.provider.OrderProvider;
import com.nb.springbootrabbitmq.vo.OrderVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.MessageDeliveryMode;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.util.UUID;

/**
 * @Author: nb
 * @Date: 2021/3/20 20:19
 * @Version 1.0
 */
@Slf4j
@Service
public class OrderService {


    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private OrderProvider orderProvider;


    public void makeOrder(OrderVO orderVO) {

        Message msg = MessageBuilder.withBody(JSON.toJSONBytes(orderVO)).build();

        log.info("订单生成成功：{}", JSON.toJSONString(orderVO));
        String exchange = "fanout_order_exchange";
        String routingKey = "";
        rabbitTemplate.convertAndSend(exchange, routingKey, msg);
        log.info("订单推送成功！");
    }

    public void sendDirectOrder(OrderVO orderVO){

        Message msg=MessageBuilder.withBody(JSON.toJSONBytes(orderVO)).build();
        log.info("订单生成成功：{}",JSON.toJSONString(orderVO));
        String exchange="direct_order_exchange";
        String routingKeySms="sms";
        String routingKeyEmail="email";
        orderProvider.senDirectMessage(msg,exchange,routingKeySms);
        orderProvider.senDirectMessage(msg,exchange,routingKeyEmail);
        log.info("订单推送成功！");
    }

    public void sendTopicOrder(OrderVO orderVO){
        Message msg=MessageBuilder.withBody(JSON.toJSONBytes(orderVO)).build();
        log.info("订单生成成功：{}",JSON.toJSONString(orderVO));
        String exchange="topic_order_exchange";
        String smsKey=String.format("%s.%s","topic.order.exchange.sms",orderVO.getId());
        String emailKey=String.format("%s.%s","topic.order.exchange.email",orderVO.getId());
        rabbitTemplate.convertAndSend(exchange,smsKey,msg);
        rabbitTemplate.convertAndSend(exchange,emailKey,msg);
        log.info("订单推送成功！");
    }

    public void sendTtlOrder(OrderVO orderVO){
        long expiration=5000;
        Message msg=MessageBuilder.withBody(JSON.toJSONBytes(orderVO)).build();
        log.info("ttl订单生成成功：{}",JSON.toJSONString(orderVO));
        String exchange="direct_order_ttl_exchange";
        rabbitTemplate.convertAndSend(exchange,"ttl",msg);
        log.info("ttl订单发送成功！");
//        rabbitTemplate.convertAndSend(exchange, "ttl", msg, new MessagePostProcessor() {
//            @Override
//            public Message postProcessMessage(Message message) throws AmqpException {
//                message.getMessageProperties().setExpiration(String.valueOf(expiration));
//                return message;
//            }
//        });
//        log.info("ttl订单发送延迟队列成功！");
    }

    public void sendDeadOrder(OrderVO orderVO){
        Message message=MessageBuilder.withBody(JSON.toJSONBytes(orderVO)).build();
        log.info("dead订单生成成功！");
        String exchange="direct_order_dead_exchange";
        rabbitTemplate.convertAndSend(exchange,"dead",message);
        log.info("dead订单推送成功！");
    }


    public void sendTestOrder(OrderVO orderVO){
        Message message=MessageBuilder.withBody(JSON.toJSONBytes(orderVO)).build();
        log.info("test订单生成成功！");
        String exchange="direct_order_exchange";
        rabbitTemplate.convertAndSend(exchange,"test",message);
        log.info("test订单推送成功！");
    }
}
