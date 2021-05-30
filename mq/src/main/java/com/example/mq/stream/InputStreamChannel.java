package com.example.mq.stream;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.context.annotation.Profile;
import org.springframework.messaging.SubscribableChannel;
import org.springframework.stereotype.Component;

@Component
@Profile("mq02")
public interface InputStreamChannel {
    String INPUT="myInput";

    @Input(INPUT)
    SubscribableChannel input();
}
