package com.yan.fishmall.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * 1. 开启服务注册发现（配置nacos）
 */
@EnableDiscoveryClient
@SpringBootApplication(exclude = (DataSourceAutoConfiguration.class))
public class FishmallGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(FishmallGatewayApplication.class, args);
	}

}
