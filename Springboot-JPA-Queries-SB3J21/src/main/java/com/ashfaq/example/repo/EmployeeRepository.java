package com.ashfaq.example.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ashfaq.example.model.Employee;

import jakarta.transaction.Transactional;
@Transactional
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

	@Modifying
//	@Transactional
	@Query("UPDATE Employee e SET e.department = :newDepartment WHERE e.name = :name")
	void updateDepartmentByName(@Param("name") String name, @Param("newDepartment") String newDepartment);


    @Modifying
    @Query("DELETE FROM Employee e WHERE e.name = :name")
    int deleteByName(@Param("name") String name);
}
