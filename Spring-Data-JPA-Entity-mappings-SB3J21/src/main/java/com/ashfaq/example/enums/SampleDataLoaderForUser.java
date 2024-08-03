package com.ashfaq.example.enums;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
@Component
class SampleDataLoaderForUser implements CommandLineRunner {

	@Autowired
  private final UserRepository userRepository;
//  private final PasswordEncoder passwordEncoder;
//
  public SampleDataLoaderForUser(UserRepository userRepository
//		  , PasswordEncoder passwordEncoder
		  ) {
      this.userRepository = userRepository;
//      this.passwordEncoder = passwordEncoder;
  }
  
  

  @Override
  public void run(String... args) throws Exception {
      Set<Role> adminRoles = new HashSet<>();
      adminRoles.add(Role.ADMIN);

      Set<Role> userRoles = new HashSet<>();
      userRoles.add(Role.USER);

      ApplicationUser admin = new ApplicationUser();
      admin.setUsername("admin");
//      admin.setPassword(passwordEncoder.encode("admin123"));
      admin.setPassword("admin123");

      admin.setRoles(adminRoles);

      ApplicationUser user = new ApplicationUser();
      user.setUsername("user");
      user.setPassword("user123");
      user.setRoles(userRoles);

      userRepository.save(admin);
      userRepository.save(user);
  }
}