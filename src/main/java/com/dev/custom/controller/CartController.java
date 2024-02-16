package com.dev.custom.controller;

import com.dev.custom.service.data.dto.CartDTO;
import com.dev.custom.service.port.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CartController {

    @Autowired
    CartService cartService;

    @PostMapping("carts")
    public ResponseEntity<Object> addCart(@RequestBody CartDTO cartDTO) {
        return ResponseEntity.ok(cartService.insertCart(cartDTO));
    }

    @PutMapping("cart")
    public ResponseEntity<Object> updateCart(@RequestBody CartDTO cartDTO) {
        return ResponseEntity.ok(cartService.updateCart(cartDTO));
    }

    @GetMapping("carts")
    public ResponseEntity<Object> getCarts(@RequestParam("pageNo") int pageNo, @RequestParam("pageSize") int pageSize) {
        /*
        pageNo : page thứ bao nhiêu
        pageSize : số lượng bản ghi trong 1 page
        */
        return ResponseEntity.ok(cartService.getCart(pageNo, pageSize));
    }
}
