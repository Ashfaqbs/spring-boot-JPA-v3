package com.ashfaq.example.query;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mango-employees")
public class MangoEmployeeController {
	
	
	
	 @Autowired
	    private MangoEmployeeService  memployeeService;
	 
	 
	 @Autowired
	    private CustomMangoEmployeRepositoryImpl  customMangoEmployeRepositoryImpl;

	    @GetMapping
	    public List<MangoEmploye> getAllEmployees() {
	        return memployeeService.getAllEmployees();
	    }

	    
	    
//		   http://localhost:8080/mango-employees/searchv1?departmentId=1&employeeIds=1,2,3&names=Alice,Bob
	    
//	    AND http://localhost:8080/mango-employees/searchv1?employeeIds=1,2,3&names=Alice,Bob when dept not passsed empty object
	    @GetMapping("/searchv1")
	    public List<MangoEmploye> searchEmployees( 
	        @RequestParam(required = false) Long departmentId,
	        @RequestParam List<Long> employeeIds,
	        @RequestParam List<String> names
	    ) {
	        return memployeeService.getEmployees(departmentId, employeeIds, names);
	    }
	    
	    
	    //Same as above with entity manager 
  
//	    http://localhost:8080/mango-employees/searchv1-with-entity-manager?departmentId=1&employeeIds=1,2,3&names=Alice,Bob 
	    @GetMapping("/searchv1-with-entity-manager")
	    public List<MangoEmploye> searchEmployeesByentityManager( 
	        @RequestParam(required = false) Long departmentId,
	        @RequestParam List<Long> employeeIds,
	        @RequestParam List<String> names
	    ) {
	        return customMangoEmployeRepositoryImpl.findByDepartmentIdAndEmployeeIdsAndNamesByEntityManager(departmentId, employeeIds, names);
	    }
	    
	    
	    
//	    http://localhost:8080/mango-employees/searchv2?departmentId=1&employeeIds=1,2,3&names=Alice,Bob&page=0&size=10    
	    @GetMapping("/searchv2")
	    public Page<MangoEmploye> searchEmployees(
	        @RequestParam Long departmentId,
	        @RequestParam List<Long> employeeIds,
	        @RequestParam List<String> names,
	        Pageable pageable
	    ) {
	        return memployeeService.getEmployeesPagination(departmentId, employeeIds, names, pageable);
	    }

	    
	    
	    //Same as above with entity manager and pagination
	    
//	    http://localhost:8080/mango-employees/searchv1-with-entity-manager-pagination?departmentId=1&employeeIds=1,2,3&names=Alice,Bob&pageNumber=0&pageSize=10	    @GetMapping("/searchv1-with-entity-manager-pagination")
	    public List<MangoEmploye> searchEmployeesByentityManagerPagination( 
	        @RequestParam(required = false) Long departmentId,
	        @RequestParam List<Long> employeeIds,
	        @RequestParam List<String> names, 
	        int pageNumber, int pageSize
	    ) {
	        return customMangoEmployeRepositoryImpl.findByDepartmentIdAndEmployeeIdsAndNamesByEntityManager(departmentId, employeeIds, names, pageNumber, pageSize);
	    }
	    
	    
	    
	    
	    
	    
	    
	    
//	    With all parameters:    
//http://localhost:8080/mango-employees/searchv3?departmentId=1&employeeIds=1,2,3&names=Alice,Bob&page=0&size=3
//	    Without names:
//http://localhost:8080/mango-employees/searchv3?departmentId=1&employeeIds=1,2,3&page=0&size=3
//	    Without employeeIds and names:
//http://localhost:8080/mango-employees/searchv3?departmentId=3&page=0&size=3
    
	  //Feature COALESCE  query with optional parameters
	     
	    @GetMapping("/searchv3")
	    public Page<MangoEmploye> searchMangoEmployees(
	        @RequestParam Long departmentId,
	        @RequestParam(required = false) List<Long> employeeIds,
	        @RequestParam(required = false) List<String> names,
	        Pageable pageable
	    ) {
	        return memployeeService.getMangoEmployeesOptional(departmentId, employeeIds, names, pageable);
	    }  
	    
//	    NULL VALUES:
//	    With null departmentId:
//	    http://localhost:8080/mango-employees/searchv4?employeeIds=1,2,3&names=Alice,Bob&page=0&size=3
//	    With all parameters:
//	  http://localhost:8080/mango-employees/searchv2?departmentId=1&employeeIds=1,2,3&names=Alice,Bob&page=0&size=3
//	    Without names:
//	    http://localhost:8080/mango-employees/searchv2?departmentId=1&employeeIds=1,2,3&page=0&size=3
//	    Without employeeIds and names:
//	    http://localhost:8080/mango-employees/searchv2?departmentId=1&page=0&size=3

	    

//	    NULL Values 
	    
	    @GetMapping("/searchv4")
	    public Page<MangoEmploye> searchEmployeesNullhandeled(
	        @RequestParam(required = false) Long departmentId,
	        @RequestParam(required = false) List<Long> employeeIds,
	        @RequestParam(required = false) List<String> names,
	        Pageable pageable
	    ) {
	        return memployeeService.getEmployees(departmentId, employeeIds, names, pageable);
	    } 
	    
	    
	    
	    @PostMapping("/add")
	    public void addEmployees(@RequestBody List<MangoEmploye> employees) {
	    	memployeeService.saveEmployees(employees);
	    }

}




