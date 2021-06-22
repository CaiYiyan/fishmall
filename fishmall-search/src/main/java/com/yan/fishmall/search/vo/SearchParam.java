package com.yan.fishmall.search.vo;

import lombok.Data;

import java.util.List;

/**
 * 封装页面所有可能传递过来的查询条件
 */
@Data
public class SearchParam {

    private String keyword; //页面传递过来的全文匹配关键字
    private Long catalog3Id; //三级分类id

    /**
     * saleCount_asc/desc
     * skuPrice_asc/desc
     * hotScore_asc/desc
     */
    private String sort;

    /**
     * 过滤条件：
     * hasStock=0/1, skuPrice=1_500/_500/500_, brandId, catalog3Id, attrs
     */
    private Integer hasStock;//是否只显示有货，0（无库存），1（有库存）
    private String skuPrice;//价格区间
    private List<Long> brandId;//按照品牌进行筛选，可多选 &brandId=1&brandId=2
    private List<String> attrs;//按照属性进行筛选，可多选

    private Integer pageNum = 1;//页码

}
