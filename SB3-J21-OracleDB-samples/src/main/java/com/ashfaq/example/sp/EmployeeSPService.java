package com.ashfaq.example.sp;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Service;

@Service
public class EmployeeSPService {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public void listAllEmployees() {
		SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate).withProcedureName("list_all_employees");

		jdbcCall.execute();
	}
	
	
	public void listAllEmployeesPrinting() {
        jdbcTemplate.execute((Connection conn) -> {
            try {
                // Enable DBMS_OUTPUT
                CallableStatement enableDbmsOutput = conn.prepareCall("{call DBMS_OUTPUT.ENABLE(100000)}");
                enableDbmsOutput.execute();

                // Call the stored procedure
                CallableStatement callProcedure = conn.prepareCall("{call list_all_employees()}");
                callProcedure.execute();

                // Fetch DBMS_OUTPUT content
                CallableStatement fetchOutput = conn.prepareCall("{call DBMS_OUTPUT.GET_LINES(?, ?)}");

                // Set output parameters (first is an array of lines, second is the count)
                fetchOutput.registerOutParameter(1, Types.ARRAY, "DBMSOUTPUT_LINESARRAY");
                fetchOutput.registerOutParameter(2, java.sql.Types.INTEGER);

                // Loop to fetch lines from DBMS_OUTPUT
                fetchOutput.execute();
                Object[] outputLines = (Object[]) fetchOutput.getArray(1).getArray();
                int lineCount = fetchOutput.getInt(2);

                // Print output lines to console
                for (int i = 0; i < lineCount; i++) {
                    System.out.println(outputLines[i].toString());
                }

                // Disable DBMS_OUTPUT after fetching
                CallableStatement disableDbmsOutput = conn.prepareCall("{call DBMS_OUTPUT.DISABLE()}");
                disableDbmsOutput.execute();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return null;
        });
    }

	public void getEmployeeById(int empId) {
		SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate).withProcedureName("get_employee_by_id");

		SqlParameterSource params = new MapSqlParameterSource().addValue("emp_id_in", empId);

		jdbcCall.execute(params);
	}
}
