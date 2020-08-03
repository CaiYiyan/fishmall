package com.yan.fishmall.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class FishmallOrderApplication {

	public static void main(String[] args) {
		SpringApplication.run(FishmallOrderApplication.class, args);
	}

}
