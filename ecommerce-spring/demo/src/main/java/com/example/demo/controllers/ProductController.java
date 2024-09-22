package com.example.demo.controllers;


import com.example.demo.DTO.Response;
import com.example.demo.models.Product;
import com.example.demo.service.interfac.IProductService;
import org.hibernate.mapping.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RequestMapping("/api/product")
@RestController
public class ProductController {
    @Autowired
    private IProductService productService;



    @PostMapping("/upload")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Response> createProduct(@RequestBody Product product){
        Response response= productService.createProduct(product);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }
    @PutMapping("/update/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Response> updateProduct(@PathVariable Long id,
                                                 @RequestBody Product prodcut){
        Response response= productService.updateProduct(id,prodcut);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }
    @GetMapping("/getAll")
    public ResponseEntity<Response> getAllproduct(){
        Response response= productService.getAllProduct();
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }
    @GetMapping("/getbyid/{id}")
    public ResponseEntity<Response> getproductById(@PathVariable Long id){
        Response response= productService.getByID(id);
        return  ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public  ResponseEntity<Response> deletePro(@PathVariable Long id){
        Response response = productService.deleteProduct(id);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }


    @GetMapping("/getbycate/{cate}")
    public ResponseEntity<?> getbycate(@PathVariable String cate){
        try{
            List<Product> products = productService.getAllProductsByCategory(cate);
            return ResponseEntity.ok().body(products);
        }catch (Exception e ){
            return ResponseEntity.status(400).body("bét tè lè nhè");
        }
    }

    @GetMapping("/getlistcate")
    public ResponseEntity<List<Product>> getCategoryProduct() {

            List<Product> productsByCategory = productService.getProductsByCategory();
            if (productsByCategory.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok().body(productsByCategory);

    }



}
