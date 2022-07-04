package com.nb.springbootrabbitmq.v2.producer.impl;

import com.nb.springbootrabbitmq.v2.comfirm.ConfirmCallback;
import com.nb.springbootrabbitmq.v2.comfirm.ReturnCallback;
import com.nb.springbootrabbitmq.v2.producer.DirectModelProducer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Correlation;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Author: lvxuan
 * @program:
 * @Date: 2022/7/3 23:11
 * @Version: 1.0
 * @motto: 而后乃将图南
 * @Description: des
 * ░░░░░░░░░░░░░░░░░░░░░░░░▄░░
 * ░░░░░░░░░▐█░░░░░░░░░░░▄▀▒▌░
 * ░░░░░░░░▐▀▒█░░░░░░░░▄▀▒▒▒▐
 * ░░░░░░░▐▄▀▒▒▀▀▀▀▄▄▄▀▒▒▒▒▒▐
 * ░░░░░▄▄▀▒░▒▒▒▒▒▒▒▒▒█▒▒▄█▒▐
 * ░░░▄▀▒▒▒░░░▒▒▒░░░▒▒▒▀██▀▒▌
 * ░░▐▒▒▒▄▄▒▒▒▒░░░▒▒▒▒▒▒▒▀▄▒▒
 * ░░▌░░▌█▀▒▒▒▒▒▄▀█▄▒▒▒▒▒▒▒█▒▐
 * ░▐░░░▒▒▒▒▒▒▒▒▌██▀▒▒░░░▒▒▒▀▄
 * ░▌░▒▄██▄▒▒▒▒▒▒▒▒▒░░░░░░▒▒▒▒
 * ▀▒▀▐▄█▄█▌▄░▀▒▒░░░░░░░░░░▒▒▒
 * You are not expected to understand this
 */
@Slf4j
@Service
public class DirectModelProducerImpl implements DirectModelProducer {
    @Resource(name = "myRabbitTemplate")
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private ConfirmCallback confirmCallback;
    @Autowired
    private ReturnCallback returnCallback;

    @Override
    public void send(Object o, String exchange, String key) {
        log.info("direct类型发送消息，exchange：{}，key：{}，消息内容：{}",
                exchange, key, o);
        rabbitTemplate.setConfirmCallback(confirmCallback);

        /**
         * 当mandatory设为true时
         * exchange会根据自身类型以及routingKey去寻找是否有合适的queue来存储消息
         * 若未找到则broker会调用basic.return方法将消息退还给生产者
         * 当mandatory设为false时，若未找到合适的queue，broker会将消息丢弃
         */
        rabbitTemplate.setMandatory(true);
        rabbitTemplate.setReturnsCallback(returnCallback);
        CorrelationData correlationData = new CorrelationData("direct123");

        rabbitTemplate.convertAndSend(exchange, key, o, correlationData);
    }
}
