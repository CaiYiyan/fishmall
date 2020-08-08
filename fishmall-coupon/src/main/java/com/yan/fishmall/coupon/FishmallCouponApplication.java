package com.yan.fishmall.coupon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * 1. 如何使用Nacos作为配置中心统一管理配置
 *  1.1 引入依赖
 *  1.2 创建一个bootstrap.properties
 *  1.3 需要给配置中心默认添加一个数据集，默认规则：“应用名.properties”
 *  1.4 给“应用名.properties”添加任何配置
 *  1.5 动态获取配置 @RefreshScope: 动态获取并刷新配置， @Value(${配置名})
 *      如果配置中心和当前应用的配置文件都配置了相同的项，优先使用配置中心的配置
 * 
 * 2. 细节
 *  2.1 命名空间：配置隔离
 *      默认： public（保留空间），默认新增的所有配置都在public空间。
 *      2.1.1 开发、测试、生产，利用命名空间来做环境隔离。
 *          注意：在bootstrap.properties配置上，需要使用哪个命名空间下的配置
 *          spring.cloud.nacos.config.namespace=e0129a1e-6bc1-44c3-b0a7-d5e73fd4e914
 *      2.1.2 每一个微服务之间互相隔离配置，每一个微服务都创建自己的命名空间，只加载自己命名空间下的所有配置
 *  2.2 配置集：所有的配置的集合
 *  2.3 配置集ID：类似文件名，Data ID
 *  2.4 配置分组： 默认所有的配置及都属于DEFAULT_GROUP
 *      11.11, 6.18
 * 
 * 项目中的使用，每个微服务创建自己的命名空间，使用配置分组区分环境：dev，test，prod
 * 
 * 3. 同时加载多个配置集
 *  3.1 微服务任何配置信息，任何配置文件都可以放在配置中心中
 *  3.2 只需要在bootstrap.properties说明加载配置中心中哪些配置文件即可
 *  3.3 @Value, @ConfigurationProperties...
 *      以前SpringBoot任何方法从配置文件中获取值，都能使用。
 *      配置中心有的优先使用配置中心的。
 * 
 */
@EnableDiscoveryClient
@SpringBootApplication
public class FishmallCouponApplication {

    public static void main(String[] args) {
        SpringApplication.run(FishmallCouponApplication.class, args);
    }

}
