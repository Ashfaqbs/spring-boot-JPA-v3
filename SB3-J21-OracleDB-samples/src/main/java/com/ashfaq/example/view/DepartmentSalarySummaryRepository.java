package com.ashfaq.example.view;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface DepartmentSalarySummaryRepository extends JpaRepository<DepartmentSalarySummary, Long> {
	List<DepartmentSalarySummary> findAll();
}
