package com.dc.order.controller;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.dc.order.entity.Order;
import com.dc.order.service.OrderService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Random;

/**
 * @PACKAGE_NAME: com.dc.order.controller
 * @NAME: OrderController
 * @USER: 25738
 * @DATE: 2021/4/27
 **/
@RestController
public class OrderController {
    @Resource
    private OrderService<Order> orderService;

    @Value("${server.port}")
    String port;

    @RequestMapping("/order/test")
    public Object tets() {
        return "我是端口" + port;
    }

    @PostMapping("/order/add")
    public Object add(@RequestBody Order order) {
        int i = new Random().nextInt(2 << 4);
        order.setAge(i);
        order.setCreateTime(new Date());
        return orderService.insert(order);
    }

    @GetMapping("/order/get/{id}")
    @DS("slave_1")
    public Object getById(@PathVariable("id") Long id) {
        return orderService.selectById(id);
    }

    @PostMapping("/order/queryList")
    @DS("master")
    public Object queryList() {
        return orderService.selectList(null);
    }
}
