package com.example.report_service.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "reports")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Report {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "order_id")
    private Long orderId;

    @Column(name = "user_id")
    private Long userId;

    private String category;
    private String productName;

    private double amount;
    private boolean refunded;

    @Column(name = "payment_method")
    private String paymentMethod;

    private String channel;
    private String region;

    @Column(name = "order_date", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime orderDate;

    @Column(name = "delivery_days")
    private int deliveryDays;

    private int rating;

    @Column(name = "is_high_priority")
    private boolean highPriority;

    // Automatically fill before persist if null
    @PrePersist
    public void prePersist() {
        if (this.orderDate == null) {
            //System.out.println("createdAt is null. Setting in code");
            this.orderDate = LocalDateTime.now();
        }
    }
}
