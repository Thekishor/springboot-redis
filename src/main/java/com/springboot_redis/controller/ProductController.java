package com.springboot_redis.controller;

import com.springboot_redis.dto.ProductRequest;
import com.springboot_redis.dto.ProductResponse;
import com.springboot_redis.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
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
    public ResponseEntity<ProductResponse> createProduct(
            @Valid @RequestBody ProductRequest productRequest
    ) {
        ProductResponse productResponse =
                productService.createProduct(productRequest);
        return new ResponseEntity<>(productResponse, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ProductResponse>> getAllProduct() {
        List<ProductResponse> productResponses = productService.getAllProducts();
        return new ResponseEntity<>(productResponses, HttpStatus.OK);
    }

    @GetMapping("/sort/{field}")
    public ResponseEntity<List<ProductResponse>> findAllProductsBySorting(
            @PathVariable("field") String field
    ) {
        List<ProductResponse> productsWithSorting =
                productService.findAllProductsWithSorting(field);
        return new ResponseEntity<>(productsWithSorting, HttpStatus.OK);
    }

    @GetMapping("/pagination/{offset}/{pageSize}")
    public ResponseEntity<Page<ProductResponse>> getProductsWithPagination(
            @PathVariable("offset") int offset,
            @PathVariable("pageSize") int pageSize
    ) {
        Page<ProductResponse> productWithPagination =
                productService.findProductWithPagination(offset, pageSize);
        return new ResponseEntity<>(productWithPagination, HttpStatus.OK);
    }

    @GetMapping("/paginationAndSort/{offset}/{pageSize}/{field}")
    public ResponseEntity<Page<ProductResponse>> getProductsWithPaginationAndSort(
            @PathVariable("offset") int offset,
            @PathVariable("pageSize") int pageSize,
            @PathVariable("field") String field
    ) {
        Page<ProductResponse> productWithPaginationAndSorting =
                productService.findProductWithPaginationAndSorting(offset, pageSize, field);
        return new ResponseEntity<>(productWithPaginationAndSorting, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getProductById(
            @PathVariable("id") Integer id
    ) {
        ProductResponse productResponse = productService.getProductByID(id);
        return new ResponseEntity<>(productResponse, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable("id") Integer id) {
        productService.deleteProduct(id);
        return new ResponseEntity<>("Product Deleted Successfully", HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponse> updateProduct(
            @PathVariable("id") Integer id,
            @Valid @RequestBody ProductRequest productRequest
    ) {
        ProductResponse productResponse = productService.updateProduct(id, productRequest);
        return new ResponseEntity<>(productResponse, HttpStatus.OK);
    }
}
