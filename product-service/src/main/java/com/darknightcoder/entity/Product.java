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
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(nullable = false)
    private String name;
    @Column(name = "product_id",unique = true, nullable = false)
    private String productId;
    private String description;
    @Column(nullable = false, precision = 10 , scale = 2)
    private BigDecimal price;
    @Column(nullable = false)
    private int stock;
    @Column(name = "created_at")
    private Instant createdAt;
    @Column(name = "updated_at")
    private Instant updatedAt;

    @PrePersist
    protected void onCreate(){
        this.createdAt= Instant.now();
        this.updatedAt= Instant.now();
    }
    @PreUpdate
    protected void onUpdate(){
        this.updatedAt= Instant.now();
    }
}
