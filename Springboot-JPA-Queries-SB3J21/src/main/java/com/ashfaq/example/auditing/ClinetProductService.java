package com.ashfaq.example.auditing;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClinetProductService {

	@Autowired
	private ClinetProductRepository productRepository;

	// Create or update a product
	public ClinetProduct saveProduct(ClinetProduct product) {
		return productRepository.save(product);
	}

	// Retrieve a product by id
	public Optional<ClinetProduct> getProductById(Long id) {
		return productRepository.findById(id);
	}

	// Retrieve all products
	public List<ClinetProduct> getAllProducts() {
		return productRepository.findAll();
	}

	// Delete a product by id
	public void deleteProduct(Long id) {
		productRepository.deleteById(id);
	}
	
	//update 
	
	public Optional<ClinetProduct> updateProductById(Long id, ClinetProduct product) {
		if(!productRepository.existsById(id)) {
			return Optional.empty();
		}
		product.setId(id);
		productRepository.save(product);
		return productRepository.findById(id);
		}
	}
