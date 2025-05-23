package com.springboot_redis.mapper;

import com.springboot_redis.dto.ProductRequest;
import com.springboot_redis.dto.ProductResponse;
import com.springboot_redis.entity.Product;
import org.springframework.stereotype.Service;

@Service
public class ProductMapper {

    public Product productDtoToProduct(ProductRequest request){
        Product product = new Product();
        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setPrice(request.getPrice());
        product.setQuantity(request.getQuantity());
        return product;
    }

    public ProductResponse productToProductResponse(Product product){
        ProductResponse productResponse = new ProductResponse();
        productResponse.setId(product.getId());
        productResponse.setName(product.getName());
        productResponse.setDescription(product.getDescription());
        productResponse.setPrice(product.getPrice());
        productResponse.setQuantity(product.getQuantity());
        return productResponse;
    }
}
