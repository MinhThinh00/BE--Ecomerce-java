package com.example.demo.DTO;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Response {
    private int statusCode;
    private String message;
    private String expirationTime;
    private String token;
    private String role;

    private UserDTO user;
    private ProductDTO productDTO;
    private CardDTO card;

    private List<UserDTO> userList;
    private List<CardDTO> cardList;
    private List<ProductDTO> productList;

}
