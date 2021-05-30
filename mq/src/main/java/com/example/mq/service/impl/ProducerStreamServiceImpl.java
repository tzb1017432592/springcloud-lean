package com.example.mq.service.impl;

import com.example.mq.service.ProducerStreamService;
import com.example.mq.stream.OutputStreamChannel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.annotation.Profile;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;


@Service
@EnableBinding(OutputStreamChannel.class)
@Profile("mq01")
@Slf4j
public class ProducerStreamServiceImpl implements ProducerStreamService {

    @Autowired
    private OutputStreamChannel outputStreamChannel;

    @Override
    public boolean rabbitMQSend(String msg) {
        return outputStreamChannel
                .output()
                .send(MessageBuilder.withPayload(msg).build());
    }
}
