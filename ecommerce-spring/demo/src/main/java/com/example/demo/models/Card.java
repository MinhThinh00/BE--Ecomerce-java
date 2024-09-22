package com.example.demo.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Entity
@Table(name="card")
    public class Card {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private long id;

        @NotNull(message = "Product is required")
        @ManyToOne
        @JoinColumn(name = "product_id", nullable = false)
        private Product product;

        @NotNull(message = "User is required")
        @ManyToOne
        @JoinColumn(name = "user_id", nullable = false)
        private User user;

        @NotNull(message = "Quantity is required")
        private int quantity;

    }
