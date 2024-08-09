package com.ashfaq.example.query;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
@Service
public class MangoEmployeeService {
	
	 @Autowired
	    private MangoEmployeeRepository employeeRepository;

	 
	 //simple query
	    public List<MangoEmploye> getEmployees(Long departmentId, List<Long> employeeIds, List<String> names) {
	        return employeeRepository.findByDepartmentIdAndEmployeeIdsAndNames(departmentId, employeeIds, names);
	    }
 
	    
	    //query with pagination
	    
	    public Page<MangoEmploye> getEmployeesPagination(Long departmentId, List<Long> employeeIds, List<String> names, Pageable pageable) {
	        return employeeRepository.findByDepartmentIdAndEmployeeIdsAndNamesAndPageable(departmentId, employeeIds, names, pageable);
	    }
	    
	    //Feature COALESCE  query with optional parameters
	    
	    public Page<MangoEmploye> getMangoEmployeesOptional(Long departmentId, List<Long> employeeIds, List<String> names, Pageable pageable) {
	        return employeeRepository.findByDepartmentIdAndOptionalParams(departmentId, employeeIds, names, pageable);
	    }
	    
	    
//	    NULL Values 
	    
	    public Page<MangoEmploye> getEmployees(Long departmentId, List<Long> employeeIds, List<String> names, Pageable pageable) {
	        return employeeRepository.findBydata(departmentId, employeeIds, names, pageable);
	    }
	    
	    
	    
	    public List<MangoEmploye> getAllEmployees() {
	        return employeeRepository.findAll();
	    }

	    public void saveEmployees(List<MangoEmploye> employees) {
	        employeeRepository.saveAll(employees);
	    }
}
