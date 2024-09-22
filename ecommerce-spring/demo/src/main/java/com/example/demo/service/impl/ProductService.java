package com.example.demo.service.impl;

import com.example.demo.DTO.ProductDTO;
import com.example.demo.DTO.Response;
import com.example.demo.exception.OurException;
import com.example.demo.models.Product;
import com.example.demo.repository.ProductRepository;
import com.example.demo.service.interfac.IProductService;
import com.example.demo.utils.utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService implements IProductService {
    @Autowired
    private ProductRepository productRepository;

    @Override
    public Response createProduct(Product product) {
        Response response= new Response();
        try {
            productRepository.save(product);
            response.setMessage("successfull");
            response.setStatusCode(200);
        }catch (Exception e){
            response.setStatusCode(500);
            response.setMessage("Error " + e.getMessage());
        }
        return response;
    }

    @Override
    public Response getAllProduct() {

        Response response = new Response();

        try {
            List<Product> products = productRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
            List<ProductDTO> productDTOS = utils.convertToProductList(products);
            response.setStatusCode(200);
            response.setMessage("successful");
            response.setProductList(productDTOS);

        } catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage("Error " + e.getMessage());
        }
        return response;
    }

    @Override
    public Response getByID(Long id) {

        Response response = new Response();
        try {
            Product product = productRepository.findById(id).orElseThrow(() -> new OurException("Room Not Found"));
            ProductDTO productDTO = utils.convertToProductDTO(product);
            response.setStatusCode(200);
            response.setMessage("successful");
            response.setProductDTO(productDTO);

        } catch (OurException e) {
            response.setStatusCode(404);
            response.setMessage(e.getMessage());
        } catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage("Error saving a room " + e.getMessage());
        }
        return response;
    }

    @Override
    public Response updateProduct(Long id, Product product) {
        Response response= new Response();

        try{
            Product pro = productRepository.findById(id).orElseThrow(() -> new OurException("Room Not Found"));
            pro.setProductName(product.getProductName());
            pro.setBrandName(product.getBrandName());
            pro.setCategory(product.getCategory());
            pro.setDescription(product.getDescription());

            pro.setPrice(product.getPrice());
            pro.setSellingPrice(product.getSellingPrice());
            Product productUpdate= productRepository.save(pro);
            ProductDTO productDTO= utils.convertToProductDTO(productUpdate);

            response.setStatusCode(200);
            response.setMessage("successful");
            response.setProductDTO(productDTO);
        }catch (OurException e){
            response.setStatusCode(404);
            response.setMessage(e.getMessage());
        }catch (Exception e){
            response.setStatusCode(500);
            response.setMessage("Error saving a room " + e.getMessage());
        }
        return response;
    }

    @Override
    public Response deleteProduct(Long id) {
        Response response = new Response();
        try {
            productRepository.findById(id).orElseThrow(() -> new OurException("product Not Found"));
            productRepository.deleteById(id);
            response.setStatusCode(200);
            response.setMessage("successful");

        } catch (OurException e) {
            response.setStatusCode(404);
            response.setMessage(e.getMessage());
        } catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage("Error: " + e.getMessage());
        }
        return response;
    }

    @Override
    public Response getByCategory(String category) {
        Response response= new Response();
        try {
            List<Product> products= productRepository.findByCategory(category);
            List<ProductDTO> productDTOs= utils.convertToProductList(products);
            response.setStatusCode(200);
            response.setProductList(productDTOs);
            response.setMessage("successful");
        }catch (Exception e){
            response.setStatusCode(500);
            response.setMessage("Error saving a room " + e.getMessage());
        }
        return response;
    }

    @Override
    public Response getBySearch(String name) {
        Response response = new Response();

        try {
            Product product = (Product) productRepository.findProductByName(name);
            ProductDTO productDTO= utils.convertToProductDTO(product);
            response.setStatusCode(200);
            response.setMessage("successful");
            response.setProductDTO(productDTO);

        } catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage("Error : " + e.getMessage());
        }
        return response;
    }

    @Override
    public List<Product> getAllProductsByCategory(String category) {
        return productRepository.findByCategory(category);
    }
    @Override
    public List<Product> getProductsByCategory() {

        List<String> categories = productRepository.findDistinctCategory();
        System.out.println(categories);
        List<Product> productsByCategory = new ArrayList<>();

        // Lấy một sản phẩm cho mỗi loại
        for (String category : categories) {
            List<Product> products = productRepository.findFirstByCategory(category);
            if (!products.isEmpty()) {
                productsByCategory.add(products.get(0)); // Lấy sản phẩm đầu tiên
            } else {
                System.out.println("No product found for category '" + category + "'");
            }
        }
        System.out.println("Products by Category: " + productsByCategory);
        return productsByCategory;
    }

}
