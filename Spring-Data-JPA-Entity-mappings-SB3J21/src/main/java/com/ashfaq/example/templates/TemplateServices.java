package com.ashfaq.example.templates;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

import com.ashfaq.example.query.MangoEmploye;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class TemplateServices {

	  @Autowired
	    private NamedParameterJdbcTemplate jdbcTemplate;

	    @Value("${mango.employee.querysql}")
	    private String query; //need to provided SQL queries i,e native qry not JQL queires

	    public List<MangoEmploye> findEmployeesByDepartmentIdAndEmployeeIdsAndNames(
	    		Long departmentId,
	    		List<Long> employeeIds,
	    		List<String> names) {
	    	 MapSqlParameterSource params = new MapSqlParameterSource();
	         params.addValue("departmentId", departmentId);
	         params.addValue("employeeIds", employeeIds);
	         params.addValue("names", names);

	    
	         // Use BeanPropertyRowMapper to map the result set columns to the MangoEmploye class properties
	         RowMapper<MangoEmploye> rowMapper = new BeanPropertyRowMapper<>(MangoEmploye.class);
	         log.info("qry : {} and  rowMapper : {}", query,rowMapper);
	         return jdbcTemplate.query(query, params, rowMapper);
	    }
}
