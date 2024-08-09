package com.ashfaq.example.enums;


import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.JpaRepository;


public interface OrderRepository extends JpaRepository<Order, Long> {
}
