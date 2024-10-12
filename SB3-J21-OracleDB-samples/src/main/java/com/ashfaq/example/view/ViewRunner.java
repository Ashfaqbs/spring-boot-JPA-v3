package com.ashfaq.example.view;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class ViewRunner implements CommandLineRunner {

	@Autowired
	DepartmentService departmentService;

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
//		List<DepartmentSalarySummary> departmentSalarySummary = departmentService.getDepartmentSalarySummary();

//		System.out.println(departmentSalarySummary                                                 );
	}
}
//o/p verified from db
//2024-10-11T20:54:11.174+05:30[0;39m [32m INFO[0;39m [35m25812[0;39m [2m---[0;39m [2m[SB3-J21-OracleDB-samples] [  restartedMain][0;39m [2m[0;39m[36mc.a.e.Sb3J21OracleDbSamplesApplication  [0;39m [2m:[0;39m Started Sb3J21OracleDbSamplesApplication in 0.826 seconds (process running for 244.076)
//Hibernate: select dss1_0.department_id,dss1_0.avg_salary,dss1_0.department_name from department_salary_summary dss1_0
//[DepartmentSalarySummary(departmentId=1, departmentName=HR, avgSalary=50000.0), DepartmentSalarySummary(departmentId=2, departmentName=Engineering, avgSalary=65000.0), DepartmentSalarySummary(departmentId=3, departmentName=Finance, avgSalary=67500.0), DepartmentSalarySummary(departmentId=4, departmentName=Marketing, avgSalary=67500.0)]
//[2m2024-10-11T20:54:11.182+05:30[0;39m [32m INFO[0;39m [35m25812[0;39m [2m---[0;39m [2m[SB3-J21-OracleDB-samples] [  restartedMain][0;39m [2m[0;39m[36m.ConditionEvaluationDeltaLoggingListener[0;39m [2m:[0;39m Condition evaluation unchanged