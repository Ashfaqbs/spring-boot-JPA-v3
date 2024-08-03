package com.ashfaq.example.enums;


import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<ApplicationUser, Long> {
//    User findByUsername(String username);
}
