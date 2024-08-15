package com.ashfaq.example.criteria;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface StudentRepository extends JpaRepository<Student, Long> {

    @Query("SELECT e FROM Student e WHERE e.id IN :ids AND e.name IN :names AND e.department IN :departments")
    List<Student> findByIdsOrNamesOrDepartments(
        @Param("ids") List<Long> ids, 
        @Param("names") List<String> names, 
        @Param("departments") List<String> departments
    );
}

