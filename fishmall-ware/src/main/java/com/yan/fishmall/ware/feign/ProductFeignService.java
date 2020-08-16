package com.yan.fishmall.ware.feign;

import com.yan.common.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient("fishmall-product")
public interface ProductFeignService {

    /**
     *  /product/skuinfo/info/{skuId}
     *  /api/product/skuinfo/info/{skuId}
     *
     *  1. 让所有请求过网关：
     *  1.1 @FeignClient("fishmall-gateway")，给网关所在的机器发请求
     *  1.2 /api/product/skuinfo/info/{skuId}
     *
     *  2. 直接让后台指定服务处理
     *  2.1 @FeignClient("fishmall-product")
     *  2.2 /product/skuinfo/info/{skuId}
     *
     * @param skuId
     * @return
     */
    @RequestMapping("/product/skuinfo/info/{skuId}")
    public R info(@PathVariable("skuId") Long skuId);
}
