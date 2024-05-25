package com.dev.custom.controller;

import com.dev.custom.service.data.dto.ProductDTO;
import com.dev.custom.service.port.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class HomeController {

    @Autowired
    ProductService productService;

    @GetMapping("/home")
    public String viewIndex(Model model,
                                @RequestParam(defaultValue = "10") int pageNo,
                                @RequestParam(defaultValue = "0") int pageSize) {
        List<ProductDTO> productDTOList = productService.getProducts(pageNo, pageSize);
        model.addAttribute("products", productDTOList);
        return "home";
    }

    @GetMapping("header")
    public String viewHeader(Model model) {
        List<ProductDTO> getProducts = productService.getProducts(10, 0);
        model.addAttribute("products", getProducts);
//        if (getProducts != null && !getProducts.isEmpty()) {
//            List<String> manufacturers = getProducts.stream().map(ProductDTO::getManufacturer).collect(Collectors.toList());
//            model.addAttribute("manufacturer", manufacturers);
//        }
        model.addAttribute("manufacturer", List.of("Apple", "Samsung", "Huawei", "Oppo"));
        return "header/header";
    }
}
