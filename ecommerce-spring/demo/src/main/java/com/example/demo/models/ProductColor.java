package com.example.demo.models;

import jakarta.persistence.*;
import lombok.Data;


@Data
@Entity
@Table(name= "product_color")
public class ProductColor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @Column(name = "color", nullable = false)
    private String color;
}
