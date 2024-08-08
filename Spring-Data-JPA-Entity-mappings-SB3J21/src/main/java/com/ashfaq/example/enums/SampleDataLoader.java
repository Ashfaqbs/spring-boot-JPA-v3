package com.ashfaq.example.enums;


import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class SampleDataLoader implements CommandLineRunner {

    private final OrderRepository orderRepository;

    public SampleDataLoader(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Order order1 = new Order();
        order1.setCustomerName("John Doe");
        order1.setStatus(OrderStatus.NEW);
        order1.setAmount(99.99);

        Order order2 = new Order();
        order2.setCustomerName("Jane Smith");
        order2.setStatus(OrderStatus.PROCESSING);
        order2.setAmount(149.99);

        Order order3 = new Order();
        order3.setCustomerName("Alice Johnson");
        order3.setStatus(OrderStatus.COMPLETED);
        order3.setAmount(199.99);

//        orderRepository.save(order1);
//        orderRepository.save(order2);
//        orderRepository.save(order3);
    }
}
