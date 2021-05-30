package com.example.mq.controller;

import com.example.mq.config.RabbitmqConfig;
import com.example.mq.config.RabbitmqDelayConfig;
import com.example.mq.service.ProducerStreamService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.MessageDeliveryMode;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;

@RestController
@RequestMapping("/rabbitmq/producer")
@Profile("mq01")
@Slf4j
public class TestController {

    public final static String ROUTING_KEY1 = "cloudtest.test1";
    public final static String DELAY_ROUTING_KEY1 = "delay_cloudtest.test1";

    @Resource
    private RabbitTemplate rabbitTemplate;

    @Resource
    private ProducerStreamService producerStreamService;

    @GetMapping("producer1/{message}")
    public String producer1(@PathVariable("message") String message) {
        rabbitTemplate.convertAndSend(RabbitmqConfig.EXCHANGE_TEST, ROUTING_KEY1, message);
        return "success";
    }

    @GetMapping("delayproducer1/{message}")
    public String delayproducer1(@PathVariable("message") String message) {
        rabbitTemplate.convertAndSend(
                RabbitmqDelayConfig.DELAY_EXCHANGE_TEST
                , DELAY_ROUTING_KEY1
                , message
                , m -> {
                    // 设置消息的持久
                    m.getMessageProperties()
                            .setDeliveryMode(MessageDeliveryMode.PERSISTENT);
                    // 设置消息延迟的时间，单位ms毫秒
                    m.getMessageProperties()
                            .setDelay(5000);
                    return m;
                });
        log.info("发送延迟消息成功【{}】",new Date());
        return "success";
    }



    @GetMapping("streamproducer1/{message}")
    public String streamproducer1(@PathVariable("message") String message) {
        return producerStreamService.rabbitMQSend(message)?"success":"fail";
    }

}
