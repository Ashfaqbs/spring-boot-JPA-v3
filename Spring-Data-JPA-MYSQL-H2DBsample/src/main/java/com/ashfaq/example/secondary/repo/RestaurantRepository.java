package com.ashfaq.example.secondary.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ashfaq.example.secondary.model.Restaurant;
import com.ashfaq.example.secondary.utils.SecondaryAppConstants;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {

//	JPQL Queries
	/*
	 * When to Use:
	 * 
	 * When you want to work with JPA entities and benefit from JPA’s abstraction
	 * layer. When the query logic is straightforward and aligns with the entity
	 * model.
	 */
	@Query(name = SecondaryAppConstants.FIND_BY_ADDRESS_QUERY)
	List<Restaurant> findByAddress(@Param("address") String address);

	@Query(name = "Restaurant.findByName")
	List<Restaurant> findByName(@Param("name") String name);

	@Query(name = "Restaurant.findByAddressAndName")
	List<Restaurant> findByAddressAndName(@Param("address") String address, @Param("name") String name);

	// Native SQL queries
	@Query(value = "SELECT * FROM restaurant WHERE id IN (:ids)", nativeQuery = true) // nativeQuerys SQL query
	List<Restaurant> findByIds(@Param("ids") List<Long> ids);
	/**
	 * Syntax and Execution:
	 * 
	 * Native SQL queries are raw SQL queries directly executed against the
	 * database. They use the nativeQuery = true attribute in the @Query annotation.
	 * They do not understand JPA entity names or mappings, only the underlying SQL
	 * table schema. When to Use:
	 * 
	 * When you need to perform operations that cannot be expressed in JPQL or when
	 * you need optimized queries for performance. When dealing with complex SQL
	 * features not supported by JPQL.
	 */

}