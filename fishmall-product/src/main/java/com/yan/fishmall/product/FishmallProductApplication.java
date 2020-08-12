package com.yan.fishmall.product;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

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
 *
 * 4. 统一的异常处理 @ControllerAdvice
 */
@MapperScan("com.yan.fishmall.product.dao")
@EnableDiscoveryClient
@SpringBootApplication
public class FishmallProductApplication {

	public static void main(String[] args) {
		SpringApplication.run(FishmallProductApplication.class, args);
	}

}
