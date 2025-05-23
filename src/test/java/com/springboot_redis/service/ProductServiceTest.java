package com.springboot_redis.service;

import com.springboot_redis.dto.ProductRequest;
import com.springboot_redis.dto.ProductResponse;
import com.springboot_redis.entity.Product;
import com.springboot_redis.mapper.ProductMapper;
import com.springboot_redis.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

    @Mock
    ProductRepository productRepository;

    @Mock
    ProductMapper productMapper;

    @Mock
    CacheManager cacheManager;

    @Mock
    Cache cache;

    @InjectMocks
    ProductServiceImpl productServiceImpl;

    @BeforeEach
    void beforeEach(){
        MockitoAnnotations.openMocks(this);

    }

    @Test
    void should_successfully_create_product(){

        ProductRequest productRequest = new ProductRequest(
                "Mobile",
                "Apple 15 pro max",
                5,
                100000L
        );

        Product product = new Product(
                "Mobile",
                "Apple 15 pro max",
                5,
                100000L
        );
        product.setId(100);

        //mock the call
        when(productMapper.productDtoToProduct(productRequest)).thenReturn(product);
        when(productRepository.save(product)).thenReturn(product);
        when(cacheManager.getCache("PRODUCT")).thenReturn(cache);
        when(productMapper.productToProductResponse(product)).thenReturn(new ProductResponse(
                100,
                "Mobile",
                "Apple 15 pro max",
                5,
                100000L
        ));

        //verify(cache).put(product.getId(), product);

        ProductResponse productResponse = productServiceImpl.createProduct(productRequest);

        //testing actual vs expected
        assertEquals(productRequest.getPrice(), productResponse.getPrice());
        assertEquals(productRequest.getName(), productResponse.getName());
        assertEquals(productRequest.getDescription(), productResponse.getDescription());

        verify(productMapper, times(1)).productDtoToProduct(productRequest);
        verify(productRepository, times(1)).save(product);
        verify(productMapper, times(1)).productToProductResponse(product);
    }
}
