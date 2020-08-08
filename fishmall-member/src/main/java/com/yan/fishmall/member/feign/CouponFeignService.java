package com.yan.fishmall.member.feign;

import com.yan.common.utils.R;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 这是一个声明式的远程调用
 */
@FeignClient("fishmall-coupon")
public interface CouponFeignService {
    
    @RequestMapping("/coupon/coupon/member/list")
    public R membercoupons();
}