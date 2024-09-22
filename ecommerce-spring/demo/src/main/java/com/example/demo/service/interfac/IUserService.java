package com.example.demo.service.interfac;

import com.example.demo.DTO.LoginRequest;
import com.example.demo.DTO.Response;
import com.example.demo.models.User;

public interface IUserService {
    Response register(User user);
    Response login(LoginRequest loginRequest);

    Response getAllUser();
    Response deleteUser(Long userId);
    Response updateUser(Long id, String role);
    Response getUserById(Long id);
    Response getMyInfor( String email);

    Long getUserIdByEmail(String email);
}
