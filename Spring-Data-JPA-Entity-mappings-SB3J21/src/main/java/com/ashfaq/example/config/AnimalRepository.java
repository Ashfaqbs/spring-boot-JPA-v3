package com.ashfaq.example.config;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
public interface AnimalRepository extends JpaRepository<Animal, Long> {
}
