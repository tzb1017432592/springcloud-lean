package com.example.mq.task;

import com.example.mq.config.RabbitmqConfig;
import com.example.mq.config.RabbitmqDelayConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Profile("mq02")
@Slf4j
public class RabbitmqConsumer {

    @RabbitListener(queues = RabbitmqConfig.QUEUE_TEST)
    public void Consumer1(String payload, Message message){
        log.info("payload:【{}】,message:【{}】",payload,message.getMessageProperties().toString());
    }

    @RabbitListener(queues = RabbitmqDelayConfig.DELAY_QUEUE_TEST)
    public void Consumer2(String payload, Message message){
        log.info("payload:【{}】,message:【{}】",payload,message.getMessageProperties().toString());
        log.info("接收延迟消息成功【{}】",new Date());
    }
}
