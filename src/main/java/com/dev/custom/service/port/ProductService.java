package com.dev.custom.service.port;

import com.dev.custom.service.data.dto.ProductDTO;

import java.util.List;
import java.util.Map;

public interface ProductService {
    ProductDTO insertProduct(ProductDTO productDTO);

    List<ProductDTO> getProducts(int pageNo, int pageSize);

    Map<String ,Object> getProductToCategory(long categoryId, int pageNo, int pageSize);

    ProductDTO getProduct(long productId);

    ProductDTO updateProduct(ProductDTO productDTO);

    boolean deleteProduct(long productId);

    int totalProduct();
}
