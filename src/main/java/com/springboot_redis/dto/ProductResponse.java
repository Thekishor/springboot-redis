package com.springboot_redis.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponse {

    private Integer id;

    private String name;

    private String description;

    private Integer quantity;

    private Long price;
}
