package com.example.demo.DTO;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.math.BigDecimal;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CardDTO {
    private long id;
    private int quantity;
    private long productID;
    private long userID;
    private String image;
    private String productName;
    private String category;
    private BigDecimal sellingPrice;

}
