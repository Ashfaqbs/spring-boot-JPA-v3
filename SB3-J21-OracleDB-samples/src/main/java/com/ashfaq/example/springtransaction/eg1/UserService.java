package com.ashfaq.example.springtransaction.eg1;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AddressRepository addressRepository;

    // Create a User with Transaction Management
    @Transactional
    public User createUser(User user) {
        return userRepository.save(user); // All operations will be rolled back if any fail
    }

    // Fetch all users (read-only)
    @Transactional(readOnly = true)
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    
//    getAllUsers() Method:
//
//    	The method getAllUsers() is annotated with @Transactional(readOnly = true), indicating that it will only read data from the database.
//    	This informs the Spring transaction manager that this transaction is read-only, potentially allowing it to optimize the transaction behavior.

    // Get a User by ID (read-only)
    @Transactional(readOnly = true)
    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    // Add Address to a User
    @Transactional
    public Address addAddress(Long userId, Address address) {
        User user = userRepository.findById(userId).orElse(null);
        if (user != null) {
            address.setUser(user);
            return addressRepository.save(address); // Rolled back if the address is not saved
        }
        return null;
    }

    // Deleting a User and all their addresses (all or nothing)
    @Transactional
    public void deleteUser(Long userId) {
        userRepository.deleteById(userId); // Rolled back if delete fails
    }
}

