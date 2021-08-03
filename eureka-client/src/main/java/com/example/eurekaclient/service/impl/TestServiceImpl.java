package com.example.eurekaclient.service.impl;

import com.example.eurekaclient.controller.ServiceInfoUtil;
import com.example.eurekaclient.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestServiceImpl implements TestService {
    @Autowired
    private ServiceInfoUtil serviceInfoUtil;

    @Autowired
    private DiscoveryClient discoveryClient;

    @Override
    public String test1() {
        List<String> services = discoveryClient.getServices();
        return "服务消费："+services+",端口号："+serviceInfoUtil.getPort();
    }
}
