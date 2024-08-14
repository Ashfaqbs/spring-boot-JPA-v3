package com.ashfaq.example.primary.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ashfaq.example.primary.model.UserDetail;
import com.ashfaq.example.primary.repo.UserRepository;


@RestController
public class UserController {
	
	@Autowired
	UserRepository userRepository;
	
	@GetMapping("/users")
	List<UserDetail> getUsers() {
		return userRepository.findAll();
	}
	
	@PostMapping(path = "/users")
	List<UserDetail> addUser(@RequestBody UserDetail userDetail) throws Exception{
		userRepository.save(userDetail);
		return userRepository.findAll();
	
	}

}