package com.dc.order;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;
import com.baomidou.dynamic.datasource.spring.boot.autoconfigure.DynamicDataSourceAutoConfiguration;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;
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
