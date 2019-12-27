package com.springcloud;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.util.unit.DataSize;

import javax.servlet.MultipartConfigElement;

@EntityScan
@MapperScan("com.springcloud.mapper")
@SpringBootApplication
@EnableTransactionManagement
public class MopApplication {

	public static void main(String[] args) {


		SpringApplication.run(MopApplication.class, args);
	}
}
