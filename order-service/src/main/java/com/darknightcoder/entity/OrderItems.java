package com.darknightcoder.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "order_item")
public class OrderItems {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;
    @Column(name = "product_id")
    private UUID productId;
    @Column(name = "product_name")
    private String productName;
    @Column(name = "purchase_price",precision = 10, scale = 2)
    private BigDecimal purchasePrice;
    private int quantity;
    @Column(precision = 12, scale = 2)
    private BigDecimal subtotal;
    private Instant createdAt;
    private Instant updatedAt;

    @PrePersist
    protected void onCreate(){
        createdAt = Instant.now();
        updatedAt = Instant.now();
    }
    @PreUpdate
    protected void onUpdate(){
        updatedAt = Instant.now();
    }

}
