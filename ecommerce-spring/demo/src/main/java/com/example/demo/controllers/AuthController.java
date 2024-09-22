package com.example.demo.controllers;


import com.example.demo.DTO.LoginRequest;
import com.example.demo.DTO.Response;
import com.example.demo.models.User;
import com.example.demo.service.interfac.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private IUserService userService;

    @PostMapping("/register")
    public ResponseEntity<Response> register(@RequestBody User user){
        Response response=userService.register(user);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @PostMapping("/login")
    public  ResponseEntity<Response> login(@RequestBody LoginRequest loginRequest){
        Response response= userService.login(loginRequest);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }
}
