package com.yan.fishmall.product;

import java.util.List;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yan.fishmall.product.entity.BrandEntity;
import com.yan.fishmall.product.service.BrandService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class FishmallProductApplicationTests {

	@Autowired
	BrandService brandService;

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
