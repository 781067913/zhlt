package com.gy;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@MapperScan("com.gy.mapper")
@ServletComponentScan
@EnableCaching
public class ZhltApplication {
	public static void main(String[] args) {
		SpringApplication.run(ZhltApplication.class, args);
	}
}
