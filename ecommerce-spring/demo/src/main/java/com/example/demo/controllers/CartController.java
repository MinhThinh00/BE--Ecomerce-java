package com.example.demo.controllers;


import com.example.demo.DTO.CardDTO;
import com.example.demo.DTO.Response;
import com.example.demo.models.Card;
import com.example.demo.service.interfac.ICardService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cart")
public class CartController {
    @Autowired
    private ICardService cardService;


    @PostMapping("/addToCart/{productId}")
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
    public ResponseEntity<Response> addTocart(HttpServletRequest request, @PathVariable Long productId, @RequestBody Card card){
        Long userId = (Long) request.getAttribute("userId");
        Response response= cardService.addToCard(userId, productId, card);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }
    @PutMapping("/updateCart/{id}")
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
    public  ResponseEntity<Response> update(@PathVariable Long id, @RequestBody Card card){
        System.out.println("Received Card: " + card);
        Response response= cardService.updateCardQuantity(id, card.getQuantity());
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
    public ResponseEntity<Response> deleteCard(@PathVariable Long id){
        Response response= cardService.deleteCard(id);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }


    @GetMapping("/getall")
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
     public ResponseEntity<List<CardDTO>> getAll(HttpServletRequest request){
        Long id= (Long) request.getAttribute("userId");
        List<CardDTO> cardDTOs = cardService.getAllCardsByUserId(id);
        return ResponseEntity.ok( cardDTOs);
    }
    @GetMapping("/gettotal")
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
    public ResponseEntity<Integer> getTotal(HttpServletRequest request) {
            Long userId= (Long) request.getAttribute("userId");
            int total = cardService.getTotalQuantityInCart(userId);
            return ResponseEntity.ok(total);
    }



}
