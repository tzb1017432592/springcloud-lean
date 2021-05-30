package com.example.mq.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
public class RabbitmqDelayConfig {
    public final static String DELAY_EXCHANGE_TEST = "delay_exchange_test";
    public final static String DELAY_QUEUE_TEST = "delay_queue_test";
    public final static String ROUTINGKEY_DELAY = "delay_cloudtest.*";

    @Bean(DELAY_EXCHANGE_TEST)
    @Profile(value = "mq01")
    public Exchange exchange(){
        return ExchangeBuilder
                .topicExchange(DELAY_EXCHANGE_TEST)
                .delayed()          // 开启支持延迟消息
                .durable(true)
                .build();
    }

    // 创建队列
    @Bean(DELAY_QUEUE_TEST)
    @Profile(value = "mq01")
    public Queue queue(){
        return new Queue(DELAY_QUEUE_TEST);
    }

    // 队列绑定交换机
    @Bean
    @Profile(value = "mq01")
    public Binding delayBinding(
            @Qualifier(DELAY_QUEUE_TEST) Queue queue,
            @Qualifier(DELAY_EXCHANGE_TEST) Exchange exchange){
        return BindingBuilder
                .bind(queue)
                .to(exchange)
                .with(ROUTINGKEY_DELAY)
                .noargs();
    }
}
