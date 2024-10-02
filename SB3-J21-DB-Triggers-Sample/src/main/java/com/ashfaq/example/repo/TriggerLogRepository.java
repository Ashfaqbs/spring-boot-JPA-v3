package com.ashfaq.example.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ashfaq.example.entity.TriggerLog;

@Repository
public interface TriggerLogRepository extends JpaRepository<TriggerLog, Long> {
    // You can add custom query methods if needed
}
