package com.yan.fishmall.ware;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class FishmallWareApplication {

	public static void main(String[] args) {
		SpringApplication.run(FishmallWareApplication.class, args);
	}

}
