package com.example.mq.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
public class RabbitmqConfig {
    public final static String EXCHANGE_TEST = "exchange_test";
    public final static String QUEUE_TEST = "queue_test";
    public final static String ROUTINGKEY_TEST = "cloudtest.*";

    @Bean(EXCHANGE_TEST)
    @Profile(value = "mq01")
    public Exchange exchange() {
        return ExchangeBuilder
                .topicExchange(EXCHANGE_TEST)
                .durable(true)
                .build();
    }

    @Bean(QUEUE_TEST)
    @Profile(value = "mq01")
    public Queue queue() {
        return new Queue(QUEUE_TEST);
    }

    @Bean
    @Profile(value = "mq01")
    public Binding binding(
            @Qualifier(EXCHANGE_TEST) Exchange exchange
            , @Qualifier(QUEUE_TEST) Queue queue) {
        return BindingBuilder
                .bind(queue)
                .to(exchange)
                .with(ROUTINGKEY_TEST)
                .noargs();
    }


}
