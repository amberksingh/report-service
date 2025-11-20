package com.example.report_service.entity;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class OrderDTO {
    private Long id;
    private String orderNumber;
    private String productName;
    private Long userId;
    private Double amount;
    private String status;
    private LocalDateTime createdAt;
}
