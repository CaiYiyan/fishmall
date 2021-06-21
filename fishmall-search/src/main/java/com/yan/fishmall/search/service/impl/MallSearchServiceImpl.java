package com.yan.fishmall.search.service.impl;

import com.yan.fishmall.search.config.FishmallElasticSearchConfig;
import com.yan.fishmall.search.constant.EsConstant;
import com.yan.fishmall.search.service.MallSearchService;
import com.yan.fishmall.search.vo.SearchParam;
import com.yan.fishmall.search.vo.SearchResult;
import org.apache.lucene.search.join.ScoreMode;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.NestedQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.RangeQueryBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.List;

@Service
public class MallSearchServiceImpl implements MallSearchService {

    @Autowired
    private RestHighLevelClient client;
    //去es进行检索
    @Override
    public SearchResult search(SearchParam param) {
        //1. 动态构建出查询需要的DSL语句
        SearchResult result = null;

        //1.准备检索请求
        SearchRequest searchRequest = buildSearchRequest(param);

        try {
            //2.执行检索请求
            SearchResponse response = client.search(searchRequest, FishmallElasticSearchConfig.COMMON_OPTIONS);

            //3.分析响应数据封装成我们需要的格式
            result = buildSearchResult(response);

        } catch (IOException e) {
            e.printStackTrace();;
        }


        return null;
    }

    /**
     * 准备检索请求
     * 模糊匹配，过滤（属性，分类，品台，价格区间，库存），排序，分页，高亮，聚合分析
     * @return
     * @param param
     */
    private SearchRequest buildSearchRequest(SearchParam param) {
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();

        /**
         * 查询：模糊匹配，过滤（属性，分类，品台，价格区间，库存）
         */
        //1.构建bool - query
        BoolQueryBuilder boolQuery = QueryBuilders.boolQuery();
        //1.1 must
        if (!StringUtils.isEmpty(param.getKeyword())) {
            boolQuery.must(QueryBuilders.matchQuery("skuTitle", param.getKeyword()));
        }
        //1.2 bool - filter
        //1.2.1 filter - 按照三级分类id查询
        if (param.getCatalog3Id() != null) {
            boolQuery.filter(QueryBuilders.termQuery("catalogId", param.getCatalog3Id()));
        }
        //1.2.2 filter - 按照品牌id查询
        if (param.getBrandId() != null && param.getBrandId().size()>0) {
            boolQuery.filter(QueryBuilders.termsQuery("brandId", param.getBrandId()));
        }
        //1.2.3 filter - 按照库存是否
        if (param.getHasStock() != null) {
            boolQuery.filter(QueryBuilders.termQuery("hasStock", param.getHasStock() == 1));
        }
        //1.2.4 filter - 按照价格区间
        if (!StringUtils.isEmpty(param.getSkuPrice())) {
            RangeQueryBuilder rangeQueryBuilder = QueryBuilders.rangeQuery("skuPrice");
            String[] prices = param.getSkuPrice().split("_");
            if (prices.length == 1) {
                if (param.getSkuPrice().startsWith("_")) {
                    rangeQueryBuilder.lte(Integer.parseInt(prices[0]));
                }else {
                    rangeQueryBuilder.gte(Integer.parseInt(prices[0]));
                }
            } else if (prices.length == 2) {
                //_6000会截取成["","6000"]
                if (!prices[0].isEmpty()) {
                    rangeQueryBuilder.gte(Integer.parseInt(prices[0]));
                }
                rangeQueryBuilder.lte(Integer.parseInt(prices[1]));
            }
            boolQuery.filter(rangeQueryBuilder);
        }

        //1.2.5 attrs-nested
        //attrs=1_5寸:8寸&2_16G:8G
        List<String> attrs = param.getAttrs();
        if (attrs != null && attrs.size() > 0) {
            attrs.forEach(attr->{
                BoolQueryBuilder queryBuilder = new BoolQueryBuilder();
                String[] attrSplit = attr.split("_");
                queryBuilder.must(QueryBuilders.termQuery("attrs.attrId", attrSplit[0]));
                String[] attrValues = attrSplit[1].split(":");
                queryBuilder.must(QueryBuilders.termsQuery("attrs.attrValue", attrValues));
                //没一个必须都得生成一个nested查询
                NestedQueryBuilder nestedQuery = QueryBuilders.nestedQuery("attrs", queryBuilder, ScoreMode.None);
                boolQuery.filter(nestedQuery);
            });
        }
        //封装所有条件
        sourceBuilder.query(boolQuery);

        /**
         * 排序，分页，高亮
         */

        /**
         * 聚合分析
         */


        SearchRequest searchRequest = new SearchRequest(new String[]{EsConstant.PRODUCT_INDEX}, sourceBuilder);
        return searchRequest;
    }

    /**
     * 构建结束数据
     * @param response
     * @return
     */
    private SearchResult buildSearchResult(SearchResponse response) {
        return null;
    }

}
