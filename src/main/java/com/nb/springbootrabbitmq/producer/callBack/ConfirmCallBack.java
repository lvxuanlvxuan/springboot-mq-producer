package com.nb.springbootrabbitmq.producer.callBack;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

/**
 * @Author: nb
 * @Date: 2021/4/12 18:36
 * @Version 1.0
 * @Description: 确认消息是否投递到broker
 */
@Slf4j
@Component
public class ConfirmCallBack implements RabbitTemplate.ConfirmCallback {

    /**
     * 重写confirm方法
     *
     * @param correlationData 对象唯一属性
     * @param ack 消息投递到broker的状态，true代表成功
     * @param cause 消息投递失败的原因
     */
    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        if(!ack){
            log.error("消息发送异常!correlationData:{},cause:{}",correlationData,cause);
        }else {
            log.info("发送者爸爸已收到发送交换机确认！");
        }
    }
}
