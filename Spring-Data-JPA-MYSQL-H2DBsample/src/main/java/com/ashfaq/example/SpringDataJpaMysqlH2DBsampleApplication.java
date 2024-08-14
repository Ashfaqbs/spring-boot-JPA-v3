package com.ashfaq.example;

import java.util.Arrays;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.ashfaq.example.secondary.model.Restaurant;
import com.ashfaq.example.secondary.repo.RestaurantRepository;

@SpringBootApplication
public class SpringDataJpaMysqlH2DBsampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringDataJpaMysqlH2DBsampleApplication.class, args);
	}

	@Bean
	public CommandLineRunner run(RestaurantRepository restaurantRepository) {
		return args -> {
			// Query and print restaurants based on address
			List<Restaurant> restaurants = restaurantRepository.findByAddress("123 Elm Street");
			restaurants.forEach(restaurant -> 
			System.out.println("ID: " + restaurant.getId() +
					", Name: " + restaurant.getName() +
					", Address: " + restaurant.getAddress())
					);

			/**
	           	Hibernate: select r1_0.id,r1_0.address,r1_0.name from Restaurant r1_0 where r1_0.address=?
				Output: ID: 1, Name: The Bistro, Address: 123 Elm Street

			 */
			
			
			
			
			

            // Query and print restaurants based on address and name
            List<Restaurant> restaurantsByAddressAndName = restaurantRepository.findByAddressAndName("123 Elm Street", "The Bistro");
            System.out.println("Restaurants found by address '123 Elm Street' and name 'The Bistro':");
            restaurantsByAddressAndName.forEach(restaurant ->
                System.out.println("ID: " + restaurant.getId() +
                        ", Name: " + restaurant.getName() +
                        ", Address: " + restaurant.getAddress())
            );
            //OP 
//            Restaurants found by address '123 Elm Street' and name 'The Bistro':
//            	ID: 1, Name: The Bistro, Address: 123 Elm Street
            
            
            
            //Native SQL queries
            
            // Define a list of IDs
            List<Long> ids = Arrays.asList(1L, 2L, 3L);

            // Query and print restaurants based on the list of IDs
            List<Restaurant> restaurantsList = restaurantRepository.findByIds(ids);
            restaurantsList.forEach(restaurant ->
                System.out.println("ID: " + restaurant.getId() +
                        ", Name: " + restaurant.getName() +
                        ", Address: " + restaurant.getAddress())
            );	
            
//            Hibernate: SELECT * FROM restaurant WHERE id IN (?,?,?)
//            ID: 1, Name: The Bistro, Address: 123 Elm Street
//            ID: 2, Name: Cafe Central, Address: 456 Oak Avenue
//            ID: 3, Name: Gourmet Haven, Address: 789 Maple Road
            
            
		};
	}
}
