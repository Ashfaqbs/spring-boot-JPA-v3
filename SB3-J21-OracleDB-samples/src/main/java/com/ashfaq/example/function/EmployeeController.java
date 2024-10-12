package com.ashfaq.example.function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;
//    http://localhost:8080/api/employees/full-name?firstName=John&lastName=Doe -- working fine
    @GetMapping("/full-name")
    public String getFullName(@RequestParam String firstName, @RequestParam String lastName) {
        return employeeService.getFullName(firstName, lastName);
    }
}
