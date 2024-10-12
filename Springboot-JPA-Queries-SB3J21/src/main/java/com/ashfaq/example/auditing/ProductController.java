package com.ashfaq.example.auditing;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/clientproducts")
public class ProductController {

	@Autowired
	private ClinetProductService productService;

	// Create a new product
	@PostMapping
	public ResponseEntity<ClinetProduct> createProduct(@RequestBody ClinetProduct product) {
		ClinetProduct savedProduct = productService.saveProduct(product);
		return new ResponseEntity<>(savedProduct, HttpStatus.CREATED);
	}

	// Get product by id
	@GetMapping("/{id}")
	public ResponseEntity<ClinetProduct> getProductById(@PathVariable Long id) {
		Optional<ClinetProduct> product = productService.getProductById(id);
		return product.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
	}

	// Get all products
	@GetMapping
	public List<ClinetProduct> getAllProducts() {
		return productService.getAllProducts();
	}

	// Delete product by id
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
		productService.deleteProduct(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	// update
	@PutMapping("/{id}")
	public Optional<ClinetProduct> updateProductById(@PathVariable("id") Long id,@RequestBody ClinetProduct product) {
		return productService.updateProductById(id, product);
	}
}
