package com.ashfaq.example.springtransaction.eg2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @PostMapping
    public ResponseEntity<String> placeOrder(@RequestBody Order order, @RequestParam Long productId) {
        try {
            String response = orderService.createOrder(order, productId);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}

//
//The client sends a request to create an order.
//The Controller receives the request and passes it to the Order Service.
//The Order Service processes the order, checks product availability, and saves the order.
//The Payment Service processes the payment for the order.
//The Notification Service sends a confirmation notification to the customer.
//If any part of the transaction fails, all operations are rolled back.