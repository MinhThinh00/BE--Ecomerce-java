package com.example.demo.controllers;


import com.example.demo.DTO.Response;
import com.example.demo.service.interfac.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private IUserService userService;

    @GetMapping("/getall")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Response> getAll(){
        Response response = userService.getAllUser();
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @PutMapping("/update/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Response> updateUser(@PathVariable Long id, @RequestBody  String role){
        Response response= userService.updateUser(id, role);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Response> deleteUser(@PathVariable Long id){
        Response response = userService.deleteUser(id);
        return  ResponseEntity.status(response.getStatusCode()).body(response);
    }
    @GetMapping("/getbyid/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Response> getUserById(@PathVariable Long id){
        Response response= userService.getUserById(id);
        return  ResponseEntity.status(response.getStatusCode()).body(response);
    }

}
