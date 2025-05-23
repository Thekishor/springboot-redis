package com.springboot_redis.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductResponse {

    private Integer id;

    private String name;

    private String description;

    private Integer quantity;

    private Long price;
}
