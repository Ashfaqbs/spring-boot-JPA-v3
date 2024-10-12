package com.ashfaq.example.auditing;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface ClinetProductRepository extends JpaRepository<ClinetProduct, Long> {

}