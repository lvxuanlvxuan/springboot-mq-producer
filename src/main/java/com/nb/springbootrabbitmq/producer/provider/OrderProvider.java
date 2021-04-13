package com.nb.springbootrabbitmq.producer.provider;

import com.nb.springbootrabbitmq.producer.callBack.ConfirmCallBack;
import com.nb.springbootrabbitmq.producer.callBack.ReturnCallBack;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageDeliveryMode;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jms.JmsProperties;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * @Author: nb
 * @Date: 2021/4/12 19:53
 * @Version 1.0
 */
@Slf4j
@Component
public class OrderProvider {

    @Autowired
    private ConfirmCallBack confirmCallBack;
    @Autowired
    private ReturnCallBack returnCallBack;
    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * 发送direct类型消息
     * @param msg
     * @param exchange
     * @param routingKey
     */
    public void senDirectMessage(Message msg,String exchange,String routingKey){

        /**
         * 消息投递到交换机确认
         */
        rabbitTemplate.setConfirmCallback(confirmCallBack);

        /**
         * 消息投递队列失败处理
         */
        rabbitTemplate.setMandatory(true);
        rabbitTemplate.setReturnsCallback(returnCallBack);


        rabbitTemplate.convertAndSend(exchange,routingKey,msg,
                message -> {
                    message.getMessageProperties().setDeliveryMode(MessageDeliveryMode.PERSISTENT);
                    return message;
                },
                new CorrelationData(UUID.randomUUID().toString()));
    }
}
