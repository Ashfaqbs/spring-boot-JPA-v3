package com.ashfaq.example.view.alternate;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ashfaq.example.view.DepartmentSalarySummary;

@Repository
public class EmployeeRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<DepartmentSalarySummary> getDepartmentSalarySummary() {
        String sql = "SELECT * FROM department_salary_summary";
        return jdbcTemplate.query(sql, new DepartmentSalarySummaryRowMapper());
    }
}
