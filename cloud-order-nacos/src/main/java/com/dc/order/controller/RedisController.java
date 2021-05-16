package com.dc.order.controller;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;

/**
 * @author : 25738
 * @version : 0.0.1
 * @date : 2021/5/2
 * @description :
 */
@RestController
public class RedisController {

    @Resource(description = "redisTemplate")
    RedisTemplate<String, Integer> redisTemplate;

    @RequestMapping("/reduce")
    public String reduce() {
        String s = "操作成功";
        Integer stock = redisTemplate.opsForValue().get("stock");
        if (stock == null) {
            redisTemplate.opsForValue().set("stock", 10);
            System.out.println("创建库存");
            stock = redisTemplate.opsForValue().get("stock");
            return "创建库存";
        }
        Long lock = redisTemplate.opsForValue().increment("lock",5L);
        try {
            if (lock != 1) {
                --stock;
                if(stock >= 0){
                    redisTemplate.opsForValue().set("stock", stock);
                    System.out.println("库存减1");
                    s = "库存减1";
                    System.out.println("剩余库存"+ redisTemplate.opsForValue().get("stock"));
                }else{
                    s = "库存不足";
                    System.out.println("剩余库存"+ redisTemplate.opsForValue().get("stock"));
                    throw new Exception("库存不足");
                }
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }finally {
            redisTemplate.opsForValue().decrement("lock");
        }
        return s;
    }
}
