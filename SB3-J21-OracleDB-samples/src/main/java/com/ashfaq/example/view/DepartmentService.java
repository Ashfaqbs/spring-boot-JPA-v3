package com.ashfaq.example.view;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DepartmentService {

    @Autowired	
    private DepartmentSalarySummaryRepository repository;

    public List<DepartmentSalarySummary> getDepartmentSalarySummary() {
        return repository.findAll();
    }
}
