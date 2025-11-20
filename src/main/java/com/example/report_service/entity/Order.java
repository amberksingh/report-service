package com.example.report_service.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;
 // matches your SQL
@ToString
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {

    private Long id;

    private String orderNumber;

    private String productName;

    private Long userId;

    private Double amount;

    private String status; // CREATED, PAID, FAILED

    private LocalDateTime createdAt;

    public Order(String orderNumber, String productName, Double amount, String status) {
        this.orderNumber = orderNumber;
        this.productName = productName;
        this.amount = amount;
        this.status = status;
    }
    public Order(Long userId, String orderNumber, String productName, Double amount, String status) {
        this.userId = userId;
        this.orderNumber = orderNumber;
        this.productName = productName;
        this.amount = amount;
        this.status = status;
    }

    // getters and setters
}

