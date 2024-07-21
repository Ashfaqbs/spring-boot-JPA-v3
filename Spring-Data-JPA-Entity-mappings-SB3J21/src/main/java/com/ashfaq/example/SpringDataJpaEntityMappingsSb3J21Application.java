package com.ashfaq.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootApplication
public class SpringDataJpaEntityMappingsSb3J21Application {

	public static void main(String[] args) {
		log.info("Spring Boot Application Started");
		SpringApplication.run(SpringDataJpaEntityMappingsSb3J21Application.class, args);
	}

}
