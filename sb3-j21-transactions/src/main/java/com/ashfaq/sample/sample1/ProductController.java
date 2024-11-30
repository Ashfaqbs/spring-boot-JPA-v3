package com.ashfaq.sample.sample1;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
public class ProductController {

	private final ProductService productService;

	public ProductController(ProductService productService) {
		this.productService = productService;
	}

	@PostMapping("/save")
	public ResponseEntity<String> saveProductWithCustomRules(@RequestBody Product product,
			@RequestParam(required = false, defaultValue = "false") boolean triggerCustomException,
			@RequestParam(required = false, defaultValue = "false") boolean triggerNullPointer) {
		try {
			productService.saveProductWithCustomRules(product, triggerCustomException, triggerNullPointer);
			return ResponseEntity.ok("Product saved successfully");
		} catch (Exception e) {
			return ResponseEntity.badRequest().body("Failed to save product: " + e.getMessage());
		}
	}
}
