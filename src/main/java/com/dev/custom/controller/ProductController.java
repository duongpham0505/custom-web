package com.dev.custom.controller;

import com.dev.custom.service.data.dto.ProductDTO;
import com.dev.custom.service.data.response.Response;
import com.dev.custom.service.port.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("products")
    public Response<Object> insertProduct(@RequestBody ProductDTO productDTO) {
        ProductDTO productResponse = productService.insertProduct(productDTO);
        return Response.builder().code(HttpStatus.OK.name()).object(Collections.singleton(productResponse)).build();
    }

    @GetMapping("add-product")
    public String addProduct(Model model) {
        model.addAttribute("productDTO", new ProductDTO());
        return "product/add-product";
    }

    @GetMapping("products")
    public List<ProductDTO> getProducts(@RequestParam(defaultValue = "10") int pageNo,
                                        @RequestParam(defaultValue = "0") int pageSize) {
        List<ProductDTO> productDTOList = productService.getProducts(pageNo, pageSize);
        return productDTOList != null && !productDTOList.isEmpty()
                ? productDTOList
                : Collections.emptyList();
    }

    @GetMapping("product/{id}")
    public ProductDTO getProduct(@PathVariable("id") long id) {
        return productService.getProduct(id);
    }

    @PutMapping("product")
    public ProductDTO updateProduct(@RequestBody ProductDTO productDTO) {
        return productService.updateProduct(productDTO);
    }

    @DeleteMapping("product/{id}")
    public boolean deleteProduct(@PathVariable("id") long id) {
        return productService.deleteProduct(id);
    }

    @GetMapping("product_total")
    public int productTotal() {
        return productService.totalProduct();
    }

    @GetMapping("{id}/products")
    public Map<String ,Object> getListProductToCategory(@PathVariable("id") long category,
                                                        @RequestParam(defaultValue = "0") int pageSize,
                                                        @RequestParam(defaultValue = "10") int pageNo) {
        return productService.getProductToCategory(category, pageNo, pageSize);

    }
}
