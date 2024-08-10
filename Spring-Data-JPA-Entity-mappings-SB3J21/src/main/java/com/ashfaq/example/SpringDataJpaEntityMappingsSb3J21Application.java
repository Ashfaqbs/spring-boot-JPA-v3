package com.ashfaq.example;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootApplication
public class SpringDataJpaEntityMappingsSb3J21Application implements CommandLineRunner {


	@Value("${mango.employee.query}")
	private String prop;
	
	
	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		log.info("prop: " + prop);
		
	}
	public static void main(String[] args) {
		log.info("Spring Boot Application Started");
		SpringApplication.run(SpringDataJpaEntityMappingsSb3J21Application.class, args);
	}

}
