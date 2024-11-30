package com.ashfaq.sample.sample1;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
public class ProductService {

	private final ProductRepository productRepository;

	public ProductService(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	@Transactional(rollbackOn = { CustomException.class,
			NullPointerException.class }, dontRollbackOn = IllegalArgumentException.class)
	public void saveProductWithCustomRules(Product product, boolean triggerCustomException,
			boolean triggerNullPointer) {

		productRepository.save(product);

		if (triggerCustomException) {
			throw new CustomException("Intentional Custom Exception to test rollback");
		}

		if (triggerNullPointer) {
			throw new NullPointerException("Intentional NullPointerException to test rollback");
		}

		// Simulating a scenario where the transaction won't roll back
		if (product.getName().equalsIgnoreCase("illegal")) {
			throw new IllegalArgumentException("Intentional IllegalArgumentException, no rollback should happen");
		}
	}
}
