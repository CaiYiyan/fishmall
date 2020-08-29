package com.yan.fishmall.product;

import java.util.List;
import java.util.UUID;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yan.fishmall.product.entity.BrandEntity;
import com.yan.fishmall.product.service.BrandService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

@SpringBootTest
class FishmallProductApplicationTests {

	@Autowired
    BrandService brandService;

	@Autowired
	StringRedisTemplate stringRedisTemplate;

	@Test
	public void testStringRedisTemplate(){
		ValueOperations<String, String> ops = stringRedisTemplate.opsForValue();

		//保存
		ops.set("hello", "world_"+ UUID.randomUUID().toString());

		//查询
		String hello = ops.get("hello");
		System.out.println("之前保存的数据是：" + hello);
	}

	@Test
	void contextLoads() {
		// BrandEntity brandEntity = new BrandEntity();
		// brandEntity.setBrandId(1289924631909642241L);
		// brandEntity.setDescript("华为");
		
		// // brandEntity.setName("华为");
		// // brandService.save(brandEntity ) ;
		// // System.out.println("保存成功...");

		// brandService.updateById(brandEntity);

		List<BrandEntity> list = brandService.list(new QueryWrapper<BrandEntity>().eq("name", "华为"));
		list.forEach((item) -> {
			System.out.println(item);
		});
	}

}
