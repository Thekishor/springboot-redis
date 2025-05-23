package com.springboot_redis.controller;

import com.springboot_redis.dto.ProductRequest;
import com.springboot_redis.dto.ProductResponse;
import com.springboot_redis.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/create")
    public ResponseEntity<ProductResponse> createProduct(@Valid @RequestBody ProductRequest productRequest){
        ProductResponse productResponse = productService.createProduct(productRequest);
        return new ResponseEntity<>(productResponse, HttpStatus.CREATED);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<ProductResponse>> getAllProduct(){
        List<ProductResponse> productResponses = productService.getAllProducts();
        return new ResponseEntity<>(productResponses, HttpStatus.OK);
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<ProductResponse> getProductById(@PathVariable("id") Integer id){
        ProductResponse productResponse = productService.getProductByID(id);
        return new ResponseEntity<>(productResponse, HttpStatus.OK);
    }

    @DeleteMapping("/deleteById/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable("id") Integer id){
        productService.deleteProduct(id);
        return new ResponseEntity<>("Product Deleted Successfully", HttpStatus.OK);
    }

    @PutMapping("/updateById/{id}")
    public ResponseEntity<ProductResponse> updateProduct(
            @PathVariable("id") Integer id,
            @Valid @RequestBody ProductRequest productRequest
    )
    {
        ProductResponse productResponse = productService.updateProduct(id, productRequest);
        return new ResponseEntity<>(productResponse, HttpStatus.OK);
    }
}
