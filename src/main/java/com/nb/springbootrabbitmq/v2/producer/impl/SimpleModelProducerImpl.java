package com.nb.springbootrabbitmq.v2.producer.impl;

import com.nb.springbootrabbitmq.v2.constance.SimpleModelConstance;
import com.nb.springbootrabbitmq.v2.producer.SimpleModelProducer;
import com.nb.springbootrabbitmq.vo.OrderVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: lvxuan
 * @program:
 * @Date: 2022/7/1 11:27
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
public class SimpleModelProducerImpl implements SimpleModelProducer {

    @Resource(name = "myRabbitTemplate")
    private RabbitTemplate rabbitTemplate;

    @Override
    public void send(OrderVO vo) {
        rabbitTemplate.convertAndSend(SimpleModelConstance.SIMPLE_MODEL_QUEUE, vo);
        log.info("发送消息成功，消息内容：{}", vo);
    }

    @Override
    public void sendBatch(List<OrderVO> orderVOS) {
        rabbitTemplate.convertAndSend(SimpleModelConstance.SIMPLE_MODEL_QUEUE_BATCH, orderVOS);
        log.info("发送消息成功，消息内容：{}", orderVOS);
    }
}
