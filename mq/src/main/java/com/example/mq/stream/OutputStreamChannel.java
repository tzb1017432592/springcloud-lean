package com.example.mq.stream;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.context.annotation.Profile;
import org.springframework.messaging.MessageChannel;
import org.springframework.stereotype.Component;

@Component
@Profile("mq01")
public interface OutputStreamChannel {

    String OUTPUT="myOutput";

    @Output(OUTPUT)
    MessageChannel output();

}
