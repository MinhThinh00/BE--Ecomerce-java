package com.example.demo.repository;

import com.example.demo.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query("select r from Product r where r.category=:category")
    List<Product> findByCategory(@Param("category") String category);

    @Query("SELECT p FROM Product p WHERE LOWER(p.productName) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<Product> findProductByName(@Param("keyword") String keyword);

    @Query("SELECT DISTINCT p.category FROM Product p")
    List<String> findDistinctCategory();

    // Truy vấn để lấy sản phẩm đầu tiên theo loại
    @Query("SELECT p FROM Product p WHERE p.category = :category ORDER BY p.id ASC")
    List<Product> findFirstByCategory(@Param("category") String category);
}
