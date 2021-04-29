package com.dc.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @Classname OrderController
 * @Description 描述
 * @Date 2021/4/29 18:26
 * @Author diaochuang
 */

@RestController
public class OrderController {

    public static final String PREFIX = "http://cloud-order-nacos:7001";

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    DiscoveryClient discoveryClient;

    @RequestMapping("test")
    public Object test() {
        return restTemplate.getForObject(PREFIX + "/order/test", String.class);
    }

    @RequestMapping("test1")
    public Object test1() {
        return discoveryClient.getInstances("cloud-order-nacos");
    }
}
