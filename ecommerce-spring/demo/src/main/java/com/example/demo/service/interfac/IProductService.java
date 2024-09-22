package com.example.demo.service.interfac;

import com.example.demo.DTO.ProductDTO;
import com.example.demo.DTO.Response;
import com.example.demo.models.Product;

import java.math.BigDecimal;
import java.util.List;

public interface IProductService {
    Response createProduct(Product product);

    Response getAllProduct();
    Response getByID(Long id);
    Response deleteProduct(Long id);
    Response getByCategory(String category);
    Response getBySearch(String name);

    Response updateProduct(Long id, Product prodcut);

    List<Product> getAllProductsByCategory(String category);
    List<Product> getProductsByCategory();
}