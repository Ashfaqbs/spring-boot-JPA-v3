package com.ashfaq.example.springtransaction.eg1;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class UserController {

	@Autowired
	private UserService userService;

	// Create a User
	@PostMapping("/users")
	public User createUser(@RequestBody User user) {
		return userService.createUser(user);
	}

	// Get All Users
	@GetMapping("/users")
	public List<User> getAllUsers() {
		return userService.getAllUsers();
	}

	// Get a User by ID
	@GetMapping("/users/{id}")
	public User getUserById(@PathVariable Long id) {
		return userService.getUserById(id);
	}

	// Add Address to a User
	@PostMapping("/users/{userId}/addresses")
	public Address addAddress(@PathVariable Long userId, @RequestBody Address address) {
		return userService.addAddress(userId, address);
	}
}
