package com.springboot_redis.service;

import com.springboot_redis.dto.ProductRequest;
import com.springboot_redis.dto.ProductResponse;

import java.util.List;

public interface ProductService {

    ProductResponse createProduct(ProductRequest productRequest);

    ProductResponse getProductByID(Integer id);

    List<ProductResponse> getAllProducts();

    void deleteProduct(Integer id);

    ProductResponse updateProduct(Integer id, ProductRequest productRequest);
}
