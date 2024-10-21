package com.ashfaq.example.springtransaction.eg2;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class OrderService {
	@Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private NotificationService notificationService;

    public String createOrder(Order order, Long productId) {
        try {
            // Check product availability
            Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

            if (product.getStock() <= 0) {
                throw new RuntimeException("Product is out of stock");
            }

            // Set order details
            order.setOrderDate(LocalDateTime.now());
            order.setTotalAmount(product.getPrice()); // Assuming one product per order

            // Save the order
            orderRepository.save(order);

            // Update product stock
            product.setStock(product.getStock() - 1);
            productRepository.save(product);

            // Process payment
            paymentService.processPayment(order);

            // Send notification
            notificationService.sendNotification(order);

            return "Order placed successfully!";
        } catch (Exception e) {
            // Handle exceptions and roll back if necessary
            throw new RuntimeException("Failed to place order: " + e.getMessage());
        }
    }
}
