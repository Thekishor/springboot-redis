package com.springboot_redis.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequest {

    @NotBlank(message = "Product name cannot be blank")
    @Size(min = 2, max = 100, message = "Product name must be between 2 and 100 characters")
    private String name;

    @NotBlank(message = "Product description cannot be blank")
    @Size(min = 5, max = 100, message = "Product description must be between 5 and 100 characters")
    private String description;

    @Positive(message = "Product quantity greater than 0")
    private Integer quantity;

    @Positive(message = "Product price contains positive")
    private Long price;
}
