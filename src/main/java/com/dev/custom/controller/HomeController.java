package com.dev.custom.controller;

import com.dev.custom.service.data.dto.ProductDTO;
import com.dev.custom.service.port.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

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

}
