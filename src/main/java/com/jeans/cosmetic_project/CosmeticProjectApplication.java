package com.jeans.cosmetic_project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication
//@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class CosmeticProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(CosmeticProjectApplication.class, args);
	}

}
