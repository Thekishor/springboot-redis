package com.springboot_redis.service;

import com.springboot_redis.entity.Product;
import com.springboot_redis.mapper.ProductMapper;
import com.springboot_redis.dto.ProductRequest;
import com.springboot_redis.dto.ProductResponse;
import com.springboot_redis.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService{

    private static final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

    private final ProductRepository productRepository;

    private final ProductMapper productMapper;

    private final CacheManager cacheManager;

    public ProductServiceImpl(ProductRepository productRepository, ProductMapper productMapper, CacheManager cacheManager) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
        this.cacheManager = cacheManager;
    }

    //@CachePut(value = "PRODUCT", key = "#result != null ? #result.getId() : null")
    @Override
    public ProductResponse createProduct(ProductRequest productRequest) {
        Product product = productMapper.productDtoToProduct(productRequest);
        Product saveProduct = productRepository.save(product);

        //Two Way Caching
        //1. Annotation based like CachePut, Cacheable, CacheEvict
        //2. Method based cache like that:
        Cache cache = cacheManager.getCache("PRODUCT");
        if (cache != null) {
            logger.info("Save Data inside Redis Cache");
            cache.put(saveProduct.getId(), saveProduct);
        }

        return productMapper.productToProductResponse(saveProduct);
    }

    @Cacheable(value = "PRODUCT", key = "#id", unless = "#result.price > 1000")
    @Override
    public ProductResponse getProductByID(Integer id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product Not Found With Id: " + id));
        System.out.println("Fetch From DB :" + product.getId());
        logger.info("Fetch Data From DB");
        return productMapper.productToProductResponse(product);
    }

    @Cacheable(value = "PRODUCT")
    @Override
    public List<ProductResponse> getAllProducts() {
        List<Product> products = productRepository.findAll();
        logger.info("Fetch ALl Data From DB");
        return products.stream().map(productMapper::productToProductResponse).toList();
    }

    @CacheEvict(value = "PRODUCT", key = "#id")
    @Override
    public void deleteProduct(Integer id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product Not Found With Id: " + id));
        productRepository.delete(product);
    }

    @CachePut(value = "PRODUCT", key = "#result != null ? #result.id() : null")
    @Override
    public ProductResponse updateProduct(Integer id, ProductRequest productRequest) {
        Product product = productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product Not Found With Id: " + id));
        product.setName(productRequest.getName());
        product.setDescription(productRequest.getDescription());
        product.setPrice(productRequest.getPrice());
        product.setQuantity(productRequest.getQuantity());
        productRepository.save(product);
        return productMapper.productToProductResponse(product);
    }
}
