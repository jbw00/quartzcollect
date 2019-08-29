package com.hamusuta.quartzcollect;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@MapperScan("com.hamusuta.quartzcollect.dao")
@SpringBootApplication
public class QuartzcollectApplication {

	public static void main(String[] args) {
		SpringApplication.run(QuartzcollectApplication.class, args);
	}

}
