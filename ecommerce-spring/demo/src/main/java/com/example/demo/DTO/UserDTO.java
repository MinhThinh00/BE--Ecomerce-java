package com.example.demo.DTO;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDTO {
    private Long id;
    private String email;
    private String username;
    private String password;
    private String profilePic;
    private String role;
    private List<CardDTO> cards= new ArrayList<>();


}
