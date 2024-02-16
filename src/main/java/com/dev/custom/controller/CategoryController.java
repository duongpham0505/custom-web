package com.dev.custom.controller;

import com.dev.custom.service.data.dto.CategoryDTO;
import com.dev.custom.service.port.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    @PostMapping("categories")
    public CategoryDTO insertCategory(@RequestBody CategoryDTO categoryDTO) {
        return categoryService.insertCategory(categoryDTO);
    }

    @GetMapping("{id}/categories")
    public Map<String, Object> getCategories(@PathVariable("id") long id,
                                             @RequestParam int pageNo,
                                             @RequestParam int pageSize) {
        return categoryService.getCategories(id, pageNo, pageSize);
    }

    @GetMapping("category_findAll")
    public Map<String, Object> getAllCategory() {
        return categoryService.getAllCategories();
    }
}
