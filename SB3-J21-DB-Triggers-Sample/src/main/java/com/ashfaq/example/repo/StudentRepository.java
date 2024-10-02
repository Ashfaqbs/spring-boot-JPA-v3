package com.ashfaq.example.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ashfaq.example.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
