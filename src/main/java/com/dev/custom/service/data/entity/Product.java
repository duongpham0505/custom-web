package com.dev.custom.service.data.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private String manufacturer;
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
}
