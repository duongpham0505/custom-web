package com.dev.custom.service.port;

import com.dev.custom.service.data.dto.CategoryDTO;

import java.util.Map;

public interface CategoryService {
    CategoryDTO insertCategory(CategoryDTO categoryDTO);

    Map<String, Object> getCategory(long categoryId);

    CategoryDTO updateCategory(CategoryDTO categoryDTO);

    boolean deleteCategoryId(long categoryId);

    Map<String, Object> getCategories(long parentId, int pageNo, int pageSize);

    Map<String, Object> getAllCategories();
}
