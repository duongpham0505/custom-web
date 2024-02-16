package com.dev.custom.service;

import com.dev.custom.repository.CartRepository;
import com.dev.custom.service.data.dto.CartDTO;
import com.dev.custom.service.data.entity.Cart;
import com.dev.custom.service.port.CartService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CartServiceImpl implements CartService {
    private static final Logger logger = LoggerFactory.getLogger(CartServiceImpl.class);

    @Autowired
    CartRepository cartRepository;
    @Autowired
    ModelMapper mapper;
    @Override
    public CartDTO insertCart(CartDTO cartDTO) {
        if (cartDTO == null) {
            return null;
        }
        Cart category = mapper.map(cartDTO, Cart.class);
        Cart cartDB = cartRepository.save(category);
        logger.info("[CART_INSERT] : {} ", cartDB);
        return mapper.map(cartDB, CartDTO.class);
    }

    @Override
    public CartDTO updateCart(CartDTO cartDTO) {
        if (cartDTO != null) {
            if (cartDTO.getQuantity() == 0) {
                cartRepository.deleteById(cartDTO.getId());
                return new CartDTO();
            }
            Cart category = mapper.map(cartDTO, Cart.class);
            Cart cartDB = cartRepository.save(category);
            return mapper.map(cartDB, CartDTO.class);
        }
        return null;
    }

    @Override
    public CartDTO getCart(long cartId) {
        Optional<Cart> cart = cartRepository.findById(cartId);
        return cart.map(value -> mapper.map(value, CartDTO.class)).orElse(null);
    }

    @Override
    public boolean deleteCart(long cartId) {
        Optional<Cart> cart = cartRepository.findById(cartId);
        if (cart.isEmpty()) {
            return false;
        }
        cartRepository.deleteById(cartId);
        return true;
    }

    @Override
    public Map<String, Object> getCart(int pageNo, int pageSize) {
        Page<Cart> cartPage = cartRepository.findAll(PageRequest.of(pageNo, pageSize));
        if (cartPage.isEmpty()) {
            return Collections.emptyMap();
        }
        List<Cart> carts = cartPage.getContent();
        List<CartDTO> cartDTOList = carts.stream().map(cart -> mapper.map(cart, CartDTO.class)).collect(Collectors.toList());
        Map<String, Object> results = new HashMap<>();
        results.put("cart", cartDTOList);
        return results;
    }
}
