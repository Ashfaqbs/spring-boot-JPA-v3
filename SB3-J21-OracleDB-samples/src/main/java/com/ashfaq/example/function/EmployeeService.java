package com.ashfaq.example.function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public String getFullName(String firstName, String lastName) {
        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate)
            .withFunctionName("get_full_name");
        SqlParameterSource params = new MapSqlParameterSource()
            .addValue("p_first_name", firstName)
            .addValue("p_last_name", lastName);

        return jdbcCall.executeFunction(String.class, params);
    }
}

