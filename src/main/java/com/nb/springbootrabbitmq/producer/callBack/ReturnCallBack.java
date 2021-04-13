package com.nb.springbootrabbitmq.producer.callBack;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.ReturnedMessage;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

/**
 * @Author: nb
 * @Date: 2021/4/12 18:47
 * @Version 1.0
 * @Description: 消息从broker投递到队列失败 处理
 */
@Slf4j
@Component
public class ReturnCallBack implements RabbitTemplate.ReturnsCallback {

    /**
     * message:消息体
     * replyCode:响应code
     * replyText:响应内容
     * exchange:交换机
     * routingKey:路由key
     * @param returnedMessage
     */
    @Override
    public void returnedMessage(ReturnedMessage returnedMessage) {
        log.error("消息投递到队列失败==>replyCode:{},repluText:{},exchange:{},routingKey:{}",
                returnedMessage.getReplyCode(),
                returnedMessage.getReplyText(),
                returnedMessage.getExchange(),
                returnedMessage.getRoutingKey());
    }
}
