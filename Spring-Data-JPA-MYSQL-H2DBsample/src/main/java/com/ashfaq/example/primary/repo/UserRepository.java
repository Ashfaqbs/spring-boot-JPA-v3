package com.ashfaq.example.primary.repo;

	

import org.springframework.data.jpa.repository.JpaRepository;

import com.ashfaq.example.primary.model.UserDetail;

public interface UserRepository extends JpaRepository<UserDetail, Long>{
}