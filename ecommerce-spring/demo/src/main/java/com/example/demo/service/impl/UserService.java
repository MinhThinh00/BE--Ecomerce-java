package com.example.demo.service.impl;

import com.example.demo.DTO.LoginRequest;
import com.example.demo.DTO.Response;
import com.example.demo.DTO.UserDTO;
import com.example.demo.exception.OurException;
import com.example.demo.models.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.interfac.IUserService;
import com.example.demo.utils.JwtUtils;
import com.example.demo.utils.utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService implements IUserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtUtils jwtUtils;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Override
    public Response register(User user) {
        Response  response= new Response();
        try{
            if(user.getRole()==null || user.getRole().isBlank()){
                user.setRole("USER");
            }
            if(userRepository.existsByEmail(user.getEmail())){
                throw new OurException("email already exists");
            }
            if (user.getCards() == null) {
                user.setCards(new ArrayList<>());
            }
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            User savedUser = userRepository.save(user);
            UserDTO userDTO= utils.mapUserModeltoUserDTO(savedUser);
            response.setStatusCode(200);
            response.setUser(userDTO);

        }catch (OurException e ){
            response.setStatusCode(400);
            response.setMessage(e.getMessage());
        }catch (Exception e){
            response.setStatusCode(500);
            response.setMessage("Error Occurred During USer Registration " + e.getMessage());
        }
        return response;
    }

    @Override
    public Response login(LoginRequest loginRequest) {
        Response response= new Response();
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(),loginRequest.getPassword()));
            var user= userRepository.findByEmail(loginRequest.getEmail()).orElseThrow(()->new OurException("User not found"));
            var token = jwtUtils.generateToken((UserDetails) user,user.getId());
            response.setToken(token);
            response.setStatusCode(200);
            response.setMessage("success");
            response.setRole(user.getRole());
        }catch (OurException e){
            response.setStatusCode(404);
            response.setMessage(e.getMessage());
        }catch (Exception e){
            response.setStatusCode(500);
            response.setMessage("Error Occurred During USer Login " + e.getMessage());
        }
        return response;
    }

    @Override
    public Response getAllUser() {
        Response response= new Response();
        try{
            List<User> userList= userRepository.findAll();
            List<UserDTO> userDTOS= utils.convertTOUserDTOList(userList);
            response.setStatusCode(200);
            response.setMessage("successful");
            response.setUserList(userDTOS);

        }catch (Exception e){
            response.setStatusCode(500);
            response.setMessage("Error getting all users " + e.getMessage());
        }
        return response;
    }

    @Override
    public Response deleteUser(Long userId) {
        Response response= new Response();
        try{
            userRepository.findById(Long.valueOf(userId)).orElseThrow(()->new OurException("user not found"));
            userRepository.deleteById(userId);
            response.setStatusCode(200);
            response.setMessage("success");
        }catch (OurException e){
            response.setStatusCode(404);
            response.setMessage(e.getMessage());
        }catch (Exception e){
            response.setStatusCode(500);
            response.setMessage("Error getting all users " + e.getMessage());
        }
        return response;
    }

    @Override
    public Response updateUser(Long id,String role) {
        Response response= new Response();
        try {
            User user= userRepository.findById(id).orElseThrow(()->  new OurException("User not found"));
            user.setRole(role);
            userRepository.save(user);
            response.setStatusCode(200);
            response.setMessage("successful");
            response.setUser(utils.mapUserModeltoUserDTO(user));
        } catch (Exception e){
            response.setStatusCode(500);
            response.setMessage("Error getting all users " + e.getMessage());
        }
        return response;
    }

    @Override
    public Response getUserById(Long userId) {
        Response response= new Response();

        try{
            User user = userRepository.findById(Long.valueOf(userId)).orElseThrow(() -> new OurException("User Not Found"));
            UserDTO userDTO = utils.mapUserModeltoUserDTO(user);
            response.setStatusCode(200);
            response.setMessage("successful");
            response.setUser(userDTO);
        }catch (OurException e){
            response.setStatusCode(404);
            response.setMessage(e.getMessage());
        }catch (Exception e){
            response.setStatusCode(500);
            response.setMessage("Error getting all users " + e.getMessage());
        }
        return response;
    }

    @Override
    public Response getMyInfor(String email) {
        Response response= new Response();
        try{
            User user = userRepository.findByEmail(email).orElseThrow(() -> new OurException("User Not Found"));
            UserDTO userDTO = utils.mapUserModeltoUserDTO(user);
            response.setStatusCode(200);
            response.setMessage("successful");
            response.setUser(userDTO);
        }catch (OurException e){
            response.setStatusCode(404);
            response.setMessage(e.getMessage());
        }catch (Exception e){
            response.setStatusCode(500);
            response.setMessage("Error getting all users " + e.getMessage());
        }
        return response;
    }
    @Override
    public Long getUserIdByEmail(String email) {
        return userRepository.findUserIdByEmail(email);
    }

}
