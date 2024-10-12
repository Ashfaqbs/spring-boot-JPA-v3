package com.ashfaq.example.view.alternate;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.ashfaq.example.view.DepartmentSalarySummary;

//RowMapper for DepartmentSalarySummary View
public class DepartmentSalarySummaryRowMapper implements RowMapper<DepartmentSalarySummary> {
	@Override
	public DepartmentSalarySummary mapRow(ResultSet rs, int rowNum) throws SQLException {
		DepartmentSalarySummary summary = new DepartmentSalarySummary();
		summary.setDepartmentId(rs.getLong("department_id"));
		summary.setDepartmentName(rs.getString("department_name"));
		summary.setAvgSalary(rs.getDouble("avg_salary"));
		return summary;
	}
}