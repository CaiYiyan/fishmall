package com.yan.fishmall.coupon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class FishmallCouponApplication {

    public static void main(String[] args) {
        SpringApplication.run(FishmallCouponApplication.class, args);
    }

}
