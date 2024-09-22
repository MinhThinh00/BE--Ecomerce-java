package com.example.demo.repository;

import com.example.demo.models.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CardRepository extends JpaRepository<Card,Long> {
    @Query("SELECT r from Card r where r.user.id=:userId")
    List<Card> findAllByUserId(@Param("userId") Long userId);

    @Query("SELECT r from Card r where r.product.id=:productId")
    Boolean existsProductInCard(@Param("productId") Long productId);
}
