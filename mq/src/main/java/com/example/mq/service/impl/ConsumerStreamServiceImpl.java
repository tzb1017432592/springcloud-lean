package com.example.mq.service.impl;

import com.example.mq.service.ConsumerStreamService;
import com.example.mq.stream.InputStreamChannel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@EnableBinding(InputStreamChannel.class)
@Profile("mq02")
@Slf4j
public class ConsumerStreamServiceImpl implements ConsumerStreamService {

    @Override
    @StreamListener(InputStreamChannel.INPUT)
    public void rabbitMQreceive(String msg) {
        log.info("stream消费到的消息:【{}】",msg);
    }
}
