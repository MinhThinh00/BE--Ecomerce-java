package com.example.demo.service.interfac;

import com.example.demo.DTO.CardDTO;
import com.example.demo.DTO.Response;
import com.example.demo.models.Card;

import java.util.List;
public interface ICardService {
    Response addToCard(Long userId, Long productId, Card card);
    Response updateCardQuantity(Long id, int quantity);
    Response deleteCard(Long id);
    List<CardDTO> getAllCardsByUserId(Long userId);
    int getTotalQuantityInCart(Long userId);
}
