package com.ashfaq.example.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ashfaq.example.model.Country;

@Repository
public interface CountryRepository extends JpaRepository<Country,String> {
}
