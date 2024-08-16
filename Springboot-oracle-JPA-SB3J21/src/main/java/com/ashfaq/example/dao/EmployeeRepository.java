package com.ashfaq.example.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ashfaq.example.model.Employee;

public interface EmployeeRepository  extends JpaRepository<Employee, Long>{

}
