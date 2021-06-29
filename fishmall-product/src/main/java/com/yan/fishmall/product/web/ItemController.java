package com.yan.fishmall.product.web;

import com.yan.fishmall.product.service.SkuInfoService;
import com.yan.fishmall.product.vo.SkuItemVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ItemController {

    @Autowired
    private SkuInfoService skuInfoService;
    /***
     * 展示当前sku的详情
     * @param skuId
     * @return
     */
    @GetMapping("/{skuId}.html")
    public String skuItem(@PathVariable("skuId") Long skuId) {//, Model model

        SkuItemVo skuItemVo = skuInfoService.item(skuId);
        //model.addAttribute("item", skuItemVo);
        System.out.println("准备查询" + skuId + "详情");
        return "item";
    }
}
