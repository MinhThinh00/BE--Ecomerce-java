package com.example.demo.service.impl;

import com.example.demo.DTO.CardDTO;
import com.example.demo.DTO.Response;
import com.example.demo.exception.OurException;
import com.example.demo.models.Card;
import com.example.demo.models.Product;
import com.example.demo.models.User;
import com.example.demo.repository.CardRepository;
import com.example.demo.repository.ProductRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.interfac.ICardService;
import com.example.demo.utils.utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CardService implements ICardService {
    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;


    @Override
    public Response addToCard(Long userId, Long productId, Card card) {
        Response response= new Response();
        try {
            User user= userRepository.findById(userId).orElseThrow(()->new OurException("user not found"));
            Product product= productRepository.findById(productId).orElseThrow(()->new OurException("product not found"));
            card.setUser(user);
            card.setProduct(product);
            if(card.getQuantity()==0) card.setQuantity(1);
            cardRepository.save(card);
            response.setStatusCode(200);
            response.setMessage("successfull");
        }catch (Exception e){
            response.setStatusCode(500);
            response.setMessage("Error Saving a booking: " + e.getMessage());
        }
        return response;
    }

    @Override
    public Response updateCardQuantity(Long id, int quantity) {
       Response response= new Response();
        try{
            Card card = cardRepository.findById(id).orElseThrow(()-> new OurException("not found"));
            card.setQuantity(quantity);
            cardRepository.save(card);
            response.setMessage("successfull");
            response.setStatusCode(200);
        }catch (Exception e){
            throw new RuntimeException("Error: " + e.getMessage());
        }
        return response;
    };

    @Override
    public Response deleteCard(Long id) {
        Response response= new Response();
        try {
            cardRepository.deleteById(id);
            response.setMessage("successful");
            response.setStatusCode(200);
        }catch (Exception e){
            response.setStatusCode(500);
            response.setMessage("Error : " + e.getMessage());
        }
        return response;
    }

    @Override
    public List<CardDTO> getAllCardsByUserId(Long userId) {
        List<Card> cards = cardRepository.findAllByUserId(userId);
        return utils.convertToCardDTOList(cards);
    }

    @Override
    public int getTotalQuantityInCart(Long userId) {
        List<Card> cards = cardRepository.findAllByUserId(userId);
        return cards.stream().mapToInt(Card::getQuantity).sum();
    }

}
