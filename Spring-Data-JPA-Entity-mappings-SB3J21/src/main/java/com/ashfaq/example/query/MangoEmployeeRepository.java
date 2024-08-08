package com.ashfaq.example.query;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
public interface MangoEmployeeRepository extends JpaRepository<MangoEmploye	, Long> {

	
	 @Query("SELECT e FROM MangoEmploye e WHERE e.departmentId = :departmentId AND e.id IN :employeeIds AND e.name IN :names")
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
	 
	 
	
	 
//	 NULL Values 
	 @Query("SELECT e FROM MangoEmploye e WHERE (:departmentId IS NULL OR e.departmentId = :departmentId) AND (:employeeIds IS NULL OR e.id IN :employeeIds) AND (:names IS NULL OR e.name IN :names)")
	    Page<MangoEmploye> findBydata(
	        @Param("departmentId") Long departmentId,
	        @Param("employeeIds") List<Long> employeeIds,
	        @Param("names") List<String> names,
	        Pageable pageable
	    );
} 
