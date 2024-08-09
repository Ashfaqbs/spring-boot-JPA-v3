package com.ashfaq.example.annotation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface BatteryRepository extends JpaRepository<Battery, Long> {
}