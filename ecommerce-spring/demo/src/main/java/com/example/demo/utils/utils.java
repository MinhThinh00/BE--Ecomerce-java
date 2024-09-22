package com.example.demo.utils;

import com.example.demo.DTO.CardDTO;
import com.example.demo.DTO.ProductDTO;
import com.example.demo.DTO.UserDTO;
import com.example.demo.models.Card;
import com.example.demo.models.Product;
import com.example.demo.models.User;

import java.util.List;
import java.util.stream.Collectors;

public class utils {
    public static UserDTO  mapUserModeltoUserDTO(User user){
        UserDTO userDTO= new UserDTO();

        userDTO.setId(user.getId());
        userDTO.setUsername(user.getUsername());
        userDTO.setEmail(user.getEmail());
        userDTO.setProfilePic(user.getProfilePic());
        userDTO.setRole(user.getRole());
        userDTO.setCards(convertToCardDTOList(user.getCards()));

        return userDTO;
    }

    public static List<UserDTO> convertTOUserDTOList(List<User> users){
        return users.stream().map(utils::mapUserModeltoUserDTO).collect(Collectors.toList());
    }

    public static ProductDTO convertToProductDTO(Product product){
        if (product == null) {
            return null;
        }

        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(product.getId());
        productDTO.setProductName(product.getProductName());
        productDTO.setBrandName(product.getBrandName());
        productDTO.setCategory(product.getCategory());
        productDTO.setProductImage(product.getProductImage());
        productDTO.setDescription(product.getDescription());
        productDTO.setPrice(product.getPrice());
        productDTO.setSellingPrice(product.getSellingPrice());
        productDTO.setProductColor(product.getProductColor());
        return productDTO;

    }
    public static List<ProductDTO> convertToProductList(List<Product> products){
        return products.stream().map(utils::convertToProductDTO).collect(Collectors.toList());
    }
    public static CardDTO convertToCardDTO(Card card){
        if (card == null) {
            return null;
        }

        CardDTO cardDTO = new CardDTO();
        cardDTO.setId(card.getId());
        cardDTO.setProductID(card.getProduct().getId());  // Chỉ lưu ID của Product
        cardDTO.setUserID(card.getUser().getId());
        cardDTO.setSellingPrice(card.getProduct().getSellingPrice());
        cardDTO.setCategory(card.getProduct().getCategory());
        cardDTO.setProductName(card.getProduct().getProductName());
        cardDTO.setQuantity(card.getQuantity());
        List<String> productImages = card.getProduct().getProductImage();
        if (productImages != null && !productImages.isEmpty()) {
            cardDTO.setImage(productImages.get(0));
        }

        return cardDTO;
    }
    public static List<CardDTO> convertToCardDTOList(List<Card> cards){
        return cards.stream().map(utils::convertToCardDTO).collect(Collectors.toList());
    }
}
