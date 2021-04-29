package com.dc.order.controller;

import com.dc.order.entity.Order;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @PACKAGE_NAME: com.dc.order.controller
 * @NAME: TestMongoCOntroller
 * @USER: 25738
 * @DATE: 2021/4/28
 **/
@RestController
public class TestMongoController {

    @Resource
    MongoTemplate mongoTemplate;

    @RequestMapping("/mongo")
    public Object queryList(){
        return mongoTemplate.find(new Query(), Order.class,"test_order");
    }

    @RequestMapping("/add")
    public void add(){
        mongoTemplate.dropCollection("test_order");
        mongoTemplate.dropCollection("test_order2");
        Order order;
        for (int i = 0; i < 100; i++) {
            order = new Order();
            order.setObjId((long) i);
            order.setName("李四"+i);
            int length = String.valueOf(i).length();
            String phone = "1571013767";
            order.setCellPhone(phone.substring(0,11-length)+i);
            order.setAge(i);
            order.setCreateTime(new Date());
            mongoTemplate.save(order,"test_order");
            mongoTemplate.save(order,"test_order2");
        }
    }
}
