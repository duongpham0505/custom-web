package com.dev.custom.service.data.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartDTO {
    private long id;
    private long userId;
    private long productId;
    private String productName;
    private double price;
    private int quantity;
    private int sale;
}
