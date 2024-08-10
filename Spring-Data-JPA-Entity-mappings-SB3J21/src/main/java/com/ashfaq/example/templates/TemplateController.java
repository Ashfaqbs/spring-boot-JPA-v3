package com.ashfaq.example.templates;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ashfaq.example.query.MangoEmploye;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/templates")
@Slf4j
public class TemplateController {

	
	 @Autowired
	    private TemplateServices templateServices;
//	 http://localhost:8080/templates/search?departmentId=1&employeeIds=1,2,3&names=Alice,Bob
	    @GetMapping("/search")
	    public List<MangoEmploye> searchEmployeesByDepartmentIdAndEmployeeIdsAndNames(
	            @RequestParam(required = false) Long departmentId,
	            @RequestParam List<Long> employeeIds,
	            @RequestParam List<String> names
	    ) {
	    	
	    	log.info("request data " + departmentId , employeeIds,names);
	        return templateServices.findEmployeesByDepartmentIdAndEmployeeIdsAndNames(departmentId, employeeIds, names);
	    }
}
