package com.ashfaq.example.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ashfaq.example.dao.CountryRepository;
import com.ashfaq.example.dto.CountryDTO;
import com.ashfaq.example.model.Country;

@Service
public class CountryService {

	@Autowired
	private CountryRepository countryRepository;

	public List<Country> getAllCountries() {
		return countryRepository.findAll();
	}


}
