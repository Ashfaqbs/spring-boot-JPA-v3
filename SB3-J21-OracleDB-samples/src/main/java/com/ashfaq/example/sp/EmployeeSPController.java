package com.ashfaq.example.sp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/employees/sp")
public class EmployeeSPController {
	@Autowired
	private EmployeeSPService employeeService;

//	 Caalling the SP but not printing the data 
	@GetMapping("/list")
	public void listAllEmployees() {
		employeeService.listAllEmployees();
	}

	// Calling SP and printing the data in console
//	http://localhost:8080/api/employees/sp/listop API 

	@GetMapping("/listop")
	public void listAllEmployeesOP() {
		employeeService.listAllEmployeesPrinting();
	}
//op
//ID: 111 Name: Sonu Khan
//ID: 112 Name: Jon Jones
//ID: 1 Name: John Doe
//ID: 2 Name: Jane Doe
//ID: 3 Name: Alice Smith
//ID: 4 Name: Bob Johnson
//ID: 5 Name: Charlie Williams
//ID: 6 Name: Diana Brown
//ID: 7 Name: Eve Davis
//ID: 8 Name: Frank Wilson

	// API http://localhost:8080/api/employees/sp/get/101 working but not printing
	@GetMapping("/get/{id}")
	public void getEmployeeById(@PathVariable int id) {
		employeeService.getEmployeeById(id);
	}
}
