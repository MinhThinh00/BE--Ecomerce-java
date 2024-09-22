package com.example.demo.DTO;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.List;
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductDTO {
    private Long id;
    private String productName;
    private String brandName;
    private String category;
    private List<String> productColor;
    private List<String> productImage;
    private String description;
    private BigDecimal price;
    private BigDecimal sellingPrice;
}
