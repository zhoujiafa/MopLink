package com.springcloud;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.util.unit.DataSize;

import javax.servlet.MultipartConfigElement;

@EntityScan
@MapperScan("com.springcloud.mapper")
@SpringBootApplication
@EnableTransactionManagement
public class MopApplication extends SpringBootServletInitializer {


	@Override
	protected SpringApplicationBuilder configure(
			SpringApplicationBuilder application) {
		//DemotestApplication类为springboot 项目的启动类
		return application.sources(MopApplication.class);
	}
	public static void main(String[] args) {


		SpringApplication.run(MopApplication.class, args);
	}
}
