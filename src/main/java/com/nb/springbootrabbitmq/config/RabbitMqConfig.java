package com.nb.springbootrabbitmq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: nb
 * @Date: 2021/3/20 20:31
 * @Version 1.0
 */
@Configuration
public class RabbitMqConfig {

    /**
     * 声明交换机
     *
     * @return
     */
    @Bean
    public FanoutExchange fanoutExchange() {
        return new FanoutExchange("fanout_order_exchange", true, false);
    }

    /**
     * 声明短信队列
     *
     * @return
     */
    @Bean
    public Queue smsQueue() {
        return new Queue("fanout.queue.sms", true);
    }

    /**
     * 声明邮件队列
     *
     * @return
     */
    @Bean
    public Queue emailQueue() {
        return new Queue("fanout.queue.email", true);
    }

    /**
     * 声明微信队列
     *
     * @return
     */
    @Bean
    public Queue wechatQueue() {
        return new Queue("fanout.queue.wechat", true);
    }

    /**
     * 短信队列绑定交换机
     *
     * @return
     */
    @Bean
    public Binding smsBinding() {
        return BindingBuilder.bind(smsQueue()).to(fanoutExchange());
    }

    /**
     * 邮件队列绑定交换机
     *
     * @return
     */
    @Bean
    public Binding emailBinding() {
        return BindingBuilder.bind(emailQueue()).to(fanoutExchange());
    }

    /**
     * 微信队列绑定交换机
     *
     * @return
     */
    @Bean
    public Binding wechatBinding() {
        return BindingBuilder.bind(wechatQueue()).to(fanoutExchange());
    }

}
