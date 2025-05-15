package com.spring.springboot_basic_app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication(scanBasePackages = {"com.spring.configuration", "com.spring.controllers", "com.spring.components", "com.spring.Repository", "com.spring.Entities","com.spring.serivces","com.spring.filters","com.spring.Utils"})
@EnableJpaRepositories(basePackages = "com.spring.Repository")
@EntityScan("com.spring.Entities")
@EnableAsync
public class SpringbootBasicAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootBasicAppApplication.class, args);
	}

}
