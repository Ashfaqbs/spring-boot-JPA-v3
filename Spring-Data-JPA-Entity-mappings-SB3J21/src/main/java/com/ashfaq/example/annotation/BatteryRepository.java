package com.ashfaq.example.annotation;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BatteryRepository extends JpaRepository<Battery, Long> {
}