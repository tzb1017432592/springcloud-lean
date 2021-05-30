package com.example.mq.service;

public interface ProducerStreamService {
    boolean rabbitMQSend(String msg);
}
