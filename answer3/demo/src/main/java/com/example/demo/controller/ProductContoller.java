package com.example.demo.controller;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.ProductDetails;
import com.example.demo.repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class ProductContoller {
    @Autowired
    private ProductRepo productRepo;

    // create product
    @PostMapping("products")
    public ProductDetails createProduct(@RequestBody ProductDetails product){
        return this.productRepo.save(product);
    }

    // get products
    @GetMapping("products")
    public List<ProductDetails> getAllProducts(){
        return this.productRepo.findAll();
    }

    // get product by id
    @GetMapping("products/{id}")
    public ResponseEntity<ProductDetails> getProductById(@PathVariable(value= "id") Long productId)
        throws ResourceNotFoundException {
        ProductDetails product = productRepo.findById(productId).orElseThrow(() -> new ResourceNotFoundException("product not found for this id :: " + productId));
        return ResponseEntity.ok().body(product);
    }

    // update product
    @PutMapping("products/{id}")
    public ResponseEntity<ProductDetails> updateProduct(@PathVariable(value = "id") Long productId, @Validated @RequestBody ProductDetails productDetails) throws ResourceNotFoundException {
        ProductDetails product = productRepo.findById(productId).orElseThrow(() -> new ResourceNotFoundException("product not found for this id :: " + productId));

        product.setName(productDetails.getName());
        product.setStock(productDetails.getStock());
        product.setPrice(productDetails.getPrice());

        return ResponseEntity.ok(this.productRepo.save(product));
    }

    // patch product

    // delete product



}
