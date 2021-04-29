package com.dc.api.controller;

import com.dc.api.service.OrderService;
import com.dc.common.entity.Order;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * @Classname OrderController
 * @Description 描述
 * @Date 2021/4/29 18:26
 * @Author diaochuang
 */

@RestController
public class OrderController {

    public static final String PREFIX = "http://cloud-order-nacos";

    @Resource
    RestTemplate restTemplate;

    @RequestMapping("test")
    public Object test() {
        return restTemplate.getForObject(PREFIX + "/order/test", String.class);
    }

    @Resource
    DiscoveryClient discoveryClient;

    @RequestMapping("test1")
    public Object test1() {
        return discoveryClient.getInstances("cloud-order-nacos");
    }


    @Resource
    OrderService orderService;

    @GetMapping("/log")
    public Object operationLog() {
        return orderService.operationLog();
    }

    @PostMapping("/order/add")
    public Object add(@RequestBody Order order) {
        return orderService.add(order);
    }

    @GetMapping("/order/get/{id}")
    public Object getById(@PathVariable("id") Long id) {
        return orderService.getById(id);
    }

    @PostMapping("/order/queryList")
    public Object queryList() {
        return orderService.queryList();
    }
}
