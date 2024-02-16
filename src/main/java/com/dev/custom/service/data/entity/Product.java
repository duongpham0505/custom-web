package com.dev.custom.service.data.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "tbl_products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    public Product(String productName, String imageUrl, String description, String color, double price) {
        this.productName = productName;
        this.imageUrl = imageUrl;
        this.description = description;
        this.color = color;
        this.price = price;
    }
}
