package com.nb.springbootrabbitmq.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: nb
 * @Date: 2021/3/21 11:21
 * @Version 1.0
 */
@Configuration
public class DirectRabbitMqConfiguration {

    @Bean
    public DirectExchange directExchange(){
        return new DirectExchange("direct_order_exchange",true,false);
    }

    /**
     * 声明过期交换机
     * @return
     */
    @Bean
    public DirectExchange directTtlExchange(){
        return new DirectExchange("direct_order_ttl_exchange",true,false);
    }

    /**
     * 声明死信交换机
     * @return
     */
    @Bean
    public DirectExchange directDeadExchange(){
        return new DirectExchange("direct_order_dead_exchange",true,false);
    }


    /**
     * 声明死信对列
     * @return
     */
    @Bean
    public Queue deadDirectQueue(){
        Map<String,Object> args=new HashMap<>();
        args.put("x-message-ttl",3000);
        //args.put("x-max-length",5);//队列内容纳消息最大个数，超过此数则放进关联队列
//        args.put("x-dead-letter-exchange","direct_order_exchange");
        args.put("x-dead-letter-exchange","topic_order_exchange");
//        args.put("x-dead-letter-routing-key","sms");
        args.put("x-dead-letter-routing-key","topic.order.exchange.email.dead");
        return new Queue("direct.queue.dead",true,false,false,args);
    }
    /**
     * 声明过期队列
     * @return
     */
    @Bean
    public Queue ttlDirectQueue(){
        Map<String,Object> args=new HashMap<>();
        args.put("x-message-ttl",3000);
        /**
         * 1.队列名
         * 2.是否持久化数据，false：mq停掉数据就会丢失
         * 3.是否排他,false代表不排他所有消费者都可以访问，true代表排他只有第一次拥有他的消费者可以访问
         * 4.是否自动删除，false代表mq停掉后自动删除队列
         */
        return new Queue("direct.queue.ttl",true,false,false,args);
    }

    @Bean
    public Queue smsDirectQueue(){
        return new Queue("direct.queue.sms",true);
    }

    @Bean
    public Queue emailDirectQueue(){
        return new Queue("direct.queue.email",true);
    }

    @Bean
    public Queue wechatDirectQueue(){
        return new Queue("direct.queue.wechat",true);
    }

    @Bean
    public Queue testDirectQueue(){
        return new Queue("direct.queue.test",true);
    }
    @Bean
    public Binding deadDirectBinding(){
        return BindingBuilder.bind(deadDirectQueue()).to(directDeadExchange()).with("dead");
    }
    @Bean
    public Binding ttlDirectBinding(){
        return BindingBuilder.bind(ttlDirectQueue()).to(directTtlExchange()).with("ttl");
    }
    @Bean
    public Binding smsDirectBinding(){
        return BindingBuilder.bind(smsDirectQueue()).to(directExchange()).with("sms");
    }

    @Bean
    public Binding emailDirectBinding(){
        return BindingBuilder.bind(emailDirectQueue()).to(directExchange()).with("email");
    }

    @Bean
    public Binding wechatDirectBinding(){
        return BindingBuilder.bind(wechatDirectQueue()).to(directExchange()).with("wechat");
    }

    @Bean
    public Binding testDirectBinding(){
        return BindingBuilder.bind(testDirectQueue()).to(directExchange()).with("test");
    }



}

