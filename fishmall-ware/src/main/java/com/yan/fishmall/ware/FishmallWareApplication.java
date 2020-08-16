package com.yan.fishmall.ware;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@MapperScan("com.yan.fishmall.ware.dao")
@EnableDiscoveryClient
@SpringBootApplication
public class FishmallWareApplication {

	public static void main(String[] args) {
		SpringApplication.run(FishmallWareApplication.class, args);
	}

}
