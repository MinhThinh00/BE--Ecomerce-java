package com.example.demo.models;


import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="product_images")
public class ProductImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @Column(name = "image_url", nullable = false)
    private String imageUrl;
}
