package com.dc.api.service;

import com.dc.common.entity.Order;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @Classname OrderService
 * @Description 描述
 * @Date 2021/4/29 18:31
 * @Author diaochuang
 */

@FeignClient(value = "cloud-order-nacos")
public interface OrderService {

    @PostMapping("/order/queryList")
    Object queryList();

    @GetMapping("/order/get/{id}")
    Object getById(@PathVariable("id") Long id);

    @PostMapping("/order/add")
    public Object add(@RequestBody Order order);
}
