package com.ashfaq.example.springtransaction.eg2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {
    @Autowired
    private NotificationRepository notificationRepository;

    public void sendNotification(Order order) {
        // Logic to send notification
        Notification notification = new Notification();
        notification.setOrderId(order.getId());
        notification.setMessage("Your order has been placed successfully!");

        notificationRepository.save(notification);
    }
}
