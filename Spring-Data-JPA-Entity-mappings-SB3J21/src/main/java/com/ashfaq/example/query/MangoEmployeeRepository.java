package com.ashfaq.example.query;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MangoEmployeeRepository extends JpaRepository<MangoEmploye	, Long> {


//	1  hard coded query
	 @Query("""
			SELECT e FROM MangoEmploye e WHERE e.departmentId = :departmentId AND e.id IN :employeeIds AND e.name IN :names
			
			""")
	 
	 
	 /*** Externalized Query Doesnot Work
	  	
//	externalized query not working
//	2
//	@Value("${mango.employee.query}")
//	private String mangoEmployeeQuery;
//  	@Query("${mango.employee.query}") //Error seen The error message indicates that HQL (Hibernate Query Language) is not able to parse the query because of the ${...} syntax, which is used for property placeholders in Spring Boot.

//3 
//	String MANGO_EMPLOYEE_QUERY = "${mango.employee.query}";
//    @Query(MANGO_EMPLOYEE_QUERY)	//this won't work as expected because the ${...} syntax is not evaluated in interfaces.
	
	Note:  if we want to use externalized query then  We can use a entity manager may work if we have to externalize the query  
	 used in CustomMangoEmployeRepositoryImpl class
	
	
	  */
//using the hardcoded here	 
	List<MangoEmploye> findByDepartmentIdAndEmployeeIdsAndNames(
	        @Param("departmentId") Long departmentId,
	        @Param("employeeIds") List<Long> employeeIds,
	        @Param("names") List<String> names
	    );	
	 
	 
	 
//	 Pagination
	 
	 @Query("SELECT e FROM MangoEmploye e WHERE e.departmentId = :departmentId AND e.id IN :employeeIds AND e.name IN :names")
	    Page<MangoEmploye> findByDepartmentIdAndEmployeeIdsAndNamesAndPageable(
	        @Param("departmentId") Long departmentId,
	        @Param("employeeIds") List<Long> employeeIds,
	        @Param("names") List<String> names,
	        Pageable pageable
	    );	
	 
	 
	 //Feature COALESCE 
	 
	 @Query("SELECT e FROM MangoEmploye e WHERE e.departmentId = :departmentId AND (COALESCE(:employeeIds, NULL) IS NULL OR e.id IN :employeeIds) AND (COALESCE(:names, NULL) IS NULL OR e.name IN :names)")
	    Page<MangoEmploye> findByDepartmentIdAndOptionalParams(
	        @Param("departmentId") Long departmentId,
	        @Param("employeeIds") List<Long> employeeIds,
	        @Param("names") List<String> names,
	        Pageable pageable
	    );
	
	 
	 /*
	  
	  
	  
	Scenario:

Purpose: COALESCE is used here to provide a fallback for null inputs. It helps to simplify conditions where you want to ensure a parameter behaves in a certain way if it is null.
Behavior: If employeeIds or names is null, COALESCE(:employeeIds, NULL) or COALESCE(:names, NULL) will evaluate to NULL. The query then checks if this is NULL to determine whether to include the condition in the query.
When to Use:

When you want to explicitly handle scenarios where a null parameter should be ignored and you want the query to proceed without that condition.
Example API Call:
GET http://localhost:8080/mango-employees/searchv2?departmentId=1&employeeIds=1,2,3&names=Alice,Bob&page=0&size=3
In this case, both employeeIds and names are provided, so the COALESCE logic will simply allow them to be used as is.
Scenario when a parameter is null:

If you call the API without providing employeeIds or names, like so:
GET http://localhost:8080/mango-employees/searchv2?departmentId=1&page=0&size=3
Here, COALESCE helps by turning those missing parameters into NULL, allowing the query to skip those conditions and only filter by departmentId.  
	  
	  
	  
	  
	  
	  
	  */
	 
	
	 
//	 NULL Values 
	 @Query("""
	 		
	 		
	 		SELECT e FROM MangoEmploye e WHERE (:departmentId IS NULL OR e.departmentId = :departmentId) AND (:employeeIds IS NULL OR e.id IN :employeeIds) AND (:names IS NULL OR e.name IN :names)
	 		
	 		
	 		
	 		""")
	    Page<MangoEmploye> findBydata(
	        @Param("departmentId") Long departmentId,
	        @Param("employeeIds") List<Long> employeeIds,
	        @Param("names") List<String> names,
	        Pageable pageable
	    );
	 
	 
/***
 * 
 * 
 * 
 * 
 
 Scenario:

Purpose: This query directly checks if the parameters themselves are NULL. If they are, the corresponding condition is ignored, effectively making that part of the query optional.
Behavior:
If departmentId is NULL, the condition (:departmentId IS NULL OR e.departmentId = :departmentId) becomes TRUE, so that condition is ignored.
The same applies to employeeIds and names.
When to Use:

When you want to build a flexible query that dynamically includes or excludes conditions based on whether input parameters are provided.
Example API Call:
GET http://localhost:8080/mango-employees/searchv2?page=0&size=3
Without any parameters, this will return all employees because all conditions are ignored.
Scenario with Partial Parameters:

If you only provide some parameters, such as:
GET http://localhost:8080/mango-employees/searchv2?departmentId=1&employeeIds=1,2,3&page=0&size=3
This query will ignore the names condition entirely and only filter by departmentId and employeeIds
 
 
 
 
 * 
 * 
 */
 
} 




/**
 
 
 Key Differences & When to Use Which:
COALESCE:

Use when you want to simplify the query conditions by providing a fallback (e.g., turn a null into a value or default to null if missing).
It’s more explicit in treating nulls and is slightly easier to read for certain use cases.
Handling NULL Directly:

Use when you want maximum flexibility in your queries, dynamically ignoring conditions if their corresponding parameters are missing or null.
This approach directly reflects the presence or absence of parameters in the final query.
 
 */
