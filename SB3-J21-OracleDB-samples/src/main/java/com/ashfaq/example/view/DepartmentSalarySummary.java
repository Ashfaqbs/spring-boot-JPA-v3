package com.ashfaq.example.view;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "department_salary_summary")
public class DepartmentSalarySummary {

	@Id
	private Long departmentId;
	private String departmentName;
	private Double avgSalary;

}
