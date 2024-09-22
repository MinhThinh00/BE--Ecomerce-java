package com.example.demo.models;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
@Entity
@Table(name="products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank(message = "productName is required")
    private String productName;

    @NotBlank(message = "brandName is required")
    private String brandName;

    @NotBlank(message = "category is required")
    private String category;

    @ElementCollection
    @CollectionTable(name = "product_images", joinColumns = @JoinColumn(name = "product_id"))
    @Column(name = "image_url")
    private List<String> productImage;
    @ElementCollection
    @CollectionTable(name = "product_color", joinColumns = @JoinColumn(name = "product_id"))
    @Column(name = "color")
    private List<String> productColor;

    @NotBlank(message = "description is required")
    private String description;

    @NotNull(message = "price is required")
    private BigDecimal price;

    @NotNull(message = "sellingPrice is required")
    private BigDecimal  sellingPrice;

}
