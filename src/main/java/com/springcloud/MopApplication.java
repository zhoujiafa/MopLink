package com.springcloud;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@EntityScan
@MapperScan("com.springcloud.mapper")
@SpringBootApplication
public class MopApplication {

	public static void main(String[] args) {
		SpringApplication.run(MopApplication.class, args);
	}

}
