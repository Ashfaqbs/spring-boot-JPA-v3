package com.ashfaq.example.criteria;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "Students")
public class Student {
    @Id
    private Long id;
    private String name;
    private String department;
}
