package com.ashfaq.example.enums;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface UserRepository extends JpaRepository<ApplicationUser, Long> {
//    User findByUsername(String username);
}
