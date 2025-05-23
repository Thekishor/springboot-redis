package com.springboot_redis.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.core.serializer.Serializer;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private String description;

    private Integer quantity;

    private Long price;

    public Product(String name, String description, Integer quantity, Long price) {
        this.name = name;
        this.description = description;
        this.quantity = quantity;
        this.price = price;
    }
}
