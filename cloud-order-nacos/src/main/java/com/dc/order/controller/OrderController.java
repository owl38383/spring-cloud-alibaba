package com.dc.order.controller;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.dc.order.entity.Order;
import com.dc.order.mapper.UserMapper;
import com.dc.order.service.OrderService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
		private OrderService orderService;

		@RequestMapping("/order/test")
		public Object tets() {
			return "dddddddddd";
		}

		@RequestMapping("/order/add")
		public Object add(@RequestBody Order order) {
				int i = new Random().nextInt(2 << 4);
				order.setAge(i);
				order.setCreateTime(new Date());
				return orderService.insert(order);
		}

		@RequestMapping("/order/get/{id}")
		@DS("slave_1")
		public Object getById(@PathVariable("id") Long id) {
				return orderService.selectById(id);
		}

		@RequestMapping("/order/queryList")
		@DS("master")
		public Object queryList() {
				return orderService.selectList(null);
		}
}
