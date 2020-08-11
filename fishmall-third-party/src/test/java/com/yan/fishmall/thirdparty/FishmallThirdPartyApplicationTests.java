package com.yan.fishmall.thirdparty;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import com.aliyun.oss.OSSClient;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class FishmallThirdPartyApplicationTests {

	@Autowired
	OSSClient ossClient;

	@Test
	void contextLoads() {
	}

	@Test
	public void testUpload() throws FileNotFoundException {
		InputStream inputStream = new FileInputStream("D:\\fish1.jpg");
		ossClient.putObject("fishmall-yan", "fish1.jpg", inputStream);

		ossClient.shutdown();

		System.out.println("上传完成...");
	}
}
