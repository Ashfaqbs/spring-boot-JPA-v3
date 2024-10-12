package com.ashfaq.example.view.alternate;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.ashfaq.example.view.DepartmentSalarySummary;

@Component
public class ViewRunner2 implements CommandLineRunner {

	@Autowired
	EmployeeRepository employeeRepository;

	@Override
	public void run(String... args) throws Exception {
//		List<DepartmentSalarySummary> departmentSalarySummary = employeeRepository.getDepartmentSalarySummary();
//		System.out.println("alternate view : " + departmentSalarySummary);
	}
}
//op
//alternate view : [DepartmentSalarySummary(departmentId=1, departmentName=HR, avgSalary=50000.0), DepartmentSalarySummary(departmentId=2, departmentName=Engineering, avgSalary=65000.0), DepartmentSalarySummary(departmentId=3, departmentName=Finance, avgSalary=67500.0), DepartmentSalarySummary(departmentId=4, departmentName=Marketing, avgSalary=67500.0)]
//Hibernate: select dss1_0.department_id,dss1_0.avg_salary,dss1_0.department_name from department_salary_summary dss1_0