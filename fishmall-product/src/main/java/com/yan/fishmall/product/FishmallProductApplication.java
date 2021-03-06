package com.yan.fishmall.product;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * 1. 整合Mybatis-Plus
 * 1.1 导入依赖
 *       <dependency>
 *           <groupId>com.baomidou</groupId>
 *           <artifactId>mybatis-plus</artifactId>
 *           <version>3.3.2</version>
 *       </dependency>
 * 1.2 配置
 * 	1.2.1 配置数据源
 * 	1.2.1 导入数据库的驱动
 * 	1.2.2 在application.yml配置数据源相关信息
 * 	1.2.2 配置Mybatis-Plus
 * 	1.2.2.1 使用@MapperScan
 * 	1.2.2.2 告诉Mybatis-Plus sql映射文件位置
 *
 * 2. 逻辑删除
 * 2.1 配置全局的逻辑删除规则（省略）
 * 2.2 配置逻辑删除的组件Bean（省略）
 * 2.3 给Bean加上逻辑删除朱姐@TableLogic
 *
 * 3. JSR303
 * 3.1 给Bean添加校验注解，并定义自己的message提示
 * 3.2 开启校验功能@Valid
 * 3.3 给校验的bean
 * 3.4 分组校验（多场景的复杂校验）
 * 	3.4.1 @NotBlank(message = "品牌名必须提交", groups = {AddGroup.class, UpdateGroup.class})
 * 		给注解校验标注什么情况需要进行校验
 * 	3.4.2 @Validated({AddGroup.class})
 * 	3.4.3 默认没有指定分组的校验注解@NotBlank，在分组校验情况下不生效，只会在@Validated生效
 * 3.5 自定义校验
 * 	3.5.1 编写一个自定义的校验注解
 *	3.5.2 编写一个自定义的校验器
 *	3.5.3 关联自定义的校验器和自定义的校验注解
 *
 * 4. 统一的异常处理
 * 4.1 编写异常处理类，使用@ControllerAdvice
 * 4.2 使用@ExceptionHandler标注方法可以处理的异常
 *
 * 5. 模板引擎
 * 5.1 thymeleaf-starter：关闭缓存
 * 5.2 静态资源都放在static文件夹下就可以按照路径直接访问
 * 5.3 页面放在template下，直接访问SpringBoot，访问项目的时候，默认会找index
 * 5.4 页面修改不重启服务器实时更新
 * 5.4.1 引入dev-tools
 * 5.4.2 修改完页面 ctrl+shift+f9 重新编译
 *
 * 6. 整合redis
 * 6.1 引入data-redis-starter
 * 6.2 简单配置redis的host等信息
 * 6.3 使用SpringBoot自动配置好的StringRedisTemplate来操作redis
 *
 * 7. 整合redisson作为分布式锁等功能框架
 * 7.1 引入依赖
 * 7.2 配置redisson，Config给容器配置一个RedissonClient实例即可
 * 7.3 参考文档使用
 *
 * 8. 整合SpringCache简化缓存开发
 * 8.1 引入依赖：spring-boot-starter-cache，spring-boot-starter-data-redis
 * 8.2 写配置
 * 8.2.1 自动配置了哪些：CacheAutoConfiguration会导入RedisCacheConfiguration，自动配好了缓存管理器RedisCacheManager
 * 8.2.2 配置使用redis作为缓存，application.yml
 * 8.3 测试使用缓存
 * 8.3.1 开启缓存功能：@EnableCaching
 * 8.3.2 使用缓存注解
 * 8.4 原理
 * CacheAutoConfiguration -> RedisCacheConfiguration - > 自动配置了RedisCacheManager -> 初始化所有的缓存
 * 	-> 每个缓存决定用什么配置 -> 如果redisCacheConfiguration有就用已有的，没有就用默认配置
 * 	-> 想改缓存的配置，只需要给容器中放一个RedisCacheConfiguration即可
 * 	-> 就会应用到当前RedisCacheManager管理的所有缓存分区中
 */
@EnableCaching
@EnableFeignClients(basePackages = "com.yan.fishmall.product.feign")
@MapperScan("com.yan.fishmall.product.dao")
@EnableDiscoveryClient
@SpringBootApplication
public class FishmallProductApplication {

	public static void main(String[] args) {
		SpringApplication.run(FishmallProductApplication.class, args);
	}

}
