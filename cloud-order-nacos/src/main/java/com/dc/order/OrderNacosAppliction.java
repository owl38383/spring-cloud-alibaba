package com.dc.order;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @PACKAGE_NAME: PACKAGE_NAME
 * @NAME: OrderNacosAppliction
 * @USER: 25738
 * @DATE: 2021/4/27
 **/
@EnableDiscoveryClient
@SpringBootApplication()
@MapperScan("com.dc.order.mapper")
public class OrderNacosAppliction {
		public static void main(String[] args) {
				SpringApplication.run(OrderNacosAppliction.class,args);
		}
}
