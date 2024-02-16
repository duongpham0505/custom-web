package com.dev.custom.service.port;

import com.dev.custom.service.data.dto.CartDTO;

import java.util.Map;

public interface CartService {
    CartDTO insertCart(CartDTO cartDTO);

    CartDTO updateCart(CartDTO cartDTO);

    CartDTO getCart(long cartId);

    boolean deleteCart(long cartId);

    Map<String, Object> getCart(int pageNo, int pageSize);
}
