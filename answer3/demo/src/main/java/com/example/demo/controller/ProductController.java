package com.example.demo.controller;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.ProductDetails;
import com.example.demo.repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class ProductController {
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
//        product.setAttributes(productDetails.getAttributes());

        final ProductDetails updatedProduct = productRepo.save(product);
        return ResponseEntity.ok(updatedProduct);
    }

    @DeleteMapping("products/{id}")
    public Map<String, Boolean> deleteProduct(@PathVariable(value = "id") Long productId) throws ResourceNotFoundException {
        ProductDetails product = productRepo.findById(productId).orElseThrow(() -> new ResourceNotFoundException("Product not found for this id :: " + productId));
        productRepo.delete(product);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
    // delete product



}
