package com.dev.custom.service.data.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductDTO {
    private long id;
    private String productName;
    private String imageUrl;
    private String description;
    private String color;
    private double price;
    private String config;
    private int quantity;
    private int status;
    private long createTime;
    private long updateTime;
    private long categoryId;
    private int sale;

    public ProductDTO(String productName, String imageUrl, String description, String color, double price) {
        this.productName = productName;
        this.imageUrl = imageUrl;
        this.description = description;
        this.color = color;
        this.price = price;
    }
}
