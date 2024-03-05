package com.dev.custom.service;

import com.dev.custom.constant.Constant;
import com.dev.custom.repository.CategoryRepository;
import com.dev.custom.repository.ProductRepository;
import com.dev.custom.service.data.dto.ProductDTO;
import com.dev.custom.service.data.entity.Category;
import com.dev.custom.service.data.entity.Product;
import com.dev.custom.service.port.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service    
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ProductDTO insertProduct(ProductDTO productDTO) {
        Category category = categoryRepository.findById(productDTO.getCategoryId());
        if (category == null) {
            return null;
        }
        Product product = modelMapper.map(productDTO, Product.class);
        Product productDb = productRepository.save(product);
        productDTO.setId(productDb.getId());
        return productDTO;
    }

    @Override
    public List<ProductDTO> getProducts(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageSize, pageNo);
        Page<Product> products = productRepository.findAll(pageable);
        if (!products.isEmpty()) {
            List<ProductDTO> productDTOList = new ArrayList<>();
            for (Product product : products.getContent()) {
                productDTOList.add(modelMapper.map(product, ProductDTO.class));
            }
            return productDTOList;
        }
        return Collections.emptyList();
    }

    @Override
    public Map<String ,Object> getProductToCategory(long categoryId, int pageNo, int pageSize) {
        Map<String ,Object> results = new HashMap<>();
        Category category = categoryRepository.findById(categoryId);
        if (category == null) {
            return Collections.emptyMap();
        }
        List<Product> products = productRepository.findAllByCategoryId(categoryId, PageRequest.of(pageSize, pageNo));
        if (products == null || products.isEmpty()) {
            return Collections.emptyMap();
        }
        List<ProductDTO> productDTOList = new ArrayList<>();
        for (Product product : products) {
            productDTOList.add(modelMapper.map(product, ProductDTO.class));
        }
        results.put(Constant.CATEGORY, category);
        results.put(Constant.PRODUCT_LIST, productDTOList);
        return results;
    }

    @Override
    public ProductDTO getProduct(long productId) {
        Product product = productRepository.getById(productId);
        return modelMapper.map(product, ProductDTO.class);
    }

    @Override
    public ProductDTO updateProduct(ProductDTO productDTO) {
        Product product = modelMapper.map(productDTO, Product.class);
        Product productDb = productRepository.save(product);
        return modelMapper.map(productDb, ProductDTO.class);
    }

    @Override
    public boolean deleteProduct(long productId) {
        productRepository.deleteById(productId);
        return true;
    }

    @Override
    public int totalProduct() {
        return productRepository.total();
    }
}
