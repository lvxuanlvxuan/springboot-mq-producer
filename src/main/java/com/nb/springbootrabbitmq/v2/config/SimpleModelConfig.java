package com.nb.springbootrabbitmq.v2.config;

import com.nb.springbootrabbitmq.v2.constance.SimpleModelConstance;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: lvxuan
 * @program:
 * @Date: 2022/7/1 14:22
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
@Configuration
public class SimpleModelConfig {

    @Bean
    public Queue simpleModelQueue() {
        /**
         * 1.队列名称
         * 2.是否持久化数据
         * 3.是否排他（仅第一个连接的消费者可访问）
         * 4.是否自动删除（rabbit关闭后删除队列）
         */
        return new Queue(SimpleModelConstance.SIMPLE_MODEL_QUEUE, true, false, false);
    }

    @Bean
    public Queue simpleModelQueueBatch() {
        /**
         * 1.队列名称
         * 2.是否持久化数据
         * 3.是否排他（仅第一个连接的消费者可访问）
         * 4.是否自动删除（rabbit关闭后删除队列）
         */
        return new Queue(SimpleModelConstance.SIMPLE_MODEL_QUEUE_BATCH, true, false, false);
    }
}
