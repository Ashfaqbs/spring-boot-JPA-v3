package com.ashfaq.example.function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

public class FunctionRunner implements CommandLineRunner {
	@Autowired
	EmployeeService empService;

	@Override
	public void run(String... args) throws Exception {
		String fullName = empService.getFullName("Sonu", "Khan");

		System.out.println("executing the function and fullName is : " + fullName);
	}
}
