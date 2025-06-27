package com.springboot_redis.service;

import com.springboot_redis.dto.ProductRequest;
import com.springboot_redis.dto.ProductResponse;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProductService {

    ProductResponse createProduct(ProductRequest productRequest);

    ProductResponse getProductByID(Integer id);

    List<ProductResponse> getAllProducts();

    List<ProductResponse> findAllProductsWithSorting(String field);

    void deleteProduct(Integer id);

    ProductResponse updateProduct(Integer id, ProductRequest productRequest);

    Page<ProductResponse> findProductWithPagination(int offset, int pageSize);

    Page<ProductResponse> findProductWithPaginationAndSorting(int offset, int pageSize, String filed);
}
