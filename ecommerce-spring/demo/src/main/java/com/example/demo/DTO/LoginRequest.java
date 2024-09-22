package com.example.demo.DTO;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginRequest {
    @NotBlank(message = "email is require")
    private String email;

    @NotBlank(message = "password is require")
    private String password;
}
