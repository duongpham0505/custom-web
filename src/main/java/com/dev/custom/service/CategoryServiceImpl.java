package com.dev.custom.service;

import com.dev.custom.constant.Constant;
import com.dev.custom.repository.CategoryRepository;
import com.dev.custom.service.data.dto.CategoryDTO;
import com.dev.custom.service.data.entity.Category;
import com.dev.custom.service.port.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    ModelMapper mapper;
    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public CategoryDTO insertCategory(CategoryDTO categoryDTO) {
        Category category = mapper.map(categoryDTO, Category.class);
        category.setCreateTime(new Date().getTime());
        if (category.getParentId() != -1L) {
            Category categoryParent = categoryRepository.findById(category.getParentId());
            if (categoryParent == null) {
                return null;
            }
        }
        Category categoryDb = categoryRepository.save(category);
        categoryDTO.setId(categoryDb.getId());
        return categoryDTO;
    }

    @Override
    public Map<String, Object> getCategory(long categoryId) {
        Map<String, Object> results = new HashMap<>();
        Category category = categoryRepository.findById(categoryId);
        if (category == null) {
            return Collections.emptyMap();
        }
        List<Category> categories = categoryRepository.findAllByParentId(categoryId);
        if (categories != null && !categories.isEmpty()) {
            List<CategoryDTO> categoryDTOList = new ArrayList<>();
            for (Category categoryDb : categories) {
                categoryDTOList.add(mapper.map(categoryDb, CategoryDTO.class));
            }
            results.put(Constant.CATEGORY_LIST, categoryDTOList);
        }
        results.put(Constant.CATEGORY, category);
        return results;
    }

    @Override
    public CategoryDTO updateCategory(CategoryDTO categoryDTO) {
        Category category = categoryRepository.findById(categoryDTO.getId());
        if (category == null) {
            return null;
        }
        if (categoryDTO.getParentId() != -1L) {
            Category parent = categoryRepository.findById(category.getParentId());
            if (parent == null) {
                return null;
            }
        }
        Category categoryUpdate = mapper.map(categoryDTO, Category.class);
        categoryRepository.save(categoryUpdate);
        return categoryDTO;
    }

    @Override
    public boolean deleteCategoryId(long categoryId) {
        Category category = categoryRepository.findById(categoryId);
        if (category == null) {
            return false;
        }
        if (category.getParentId() == -1L) {
            categoryRepository.deleteById(categoryId);
            return true;
        }
        List<Category> categoryList = categoryRepository.findAllByParentId(categoryId);
        if (!categoryList.isEmpty()) {
            categoryList.forEach(c -> c.setInActive(false));
            categoryRepository.saveAll(categoryList);
        }
        categoryRepository.deleteById(categoryId);
        return true;
    }

    @Override
    public Map<String, Object> getCategories(long parentId, int pageNo, int pageSize) {
        Map<String, Object> result = new HashMap<>();
        PageRequest request = PageRequest.of(pageNo, pageSize);
        if (parentId != -1L) {
            Category category = categoryRepository.findById(parentId);
            if (category == null) {
                return Collections.emptyMap();
            }
            result.put(Constant.CATEGORY_PARENT, category);
        }
        List<CategoryDTO> results = new ArrayList<>();
        List<Category> getCategories = parentId != -1L
                ? categoryRepository.findAllByParentId(parentId)
                : categoryRepository.findAll(request).getContent();
        if (getCategories != null && !getCategories.isEmpty()) {
            for (Category category : getCategories) {
                results.add(mapper.map(category, CategoryDTO.class));
            }
        }
        result.put(Constant.CATEGORY_CHILD, results);

        return result;
    }

    @Override
    public Map<String, Object> getAllCategories() {
        Map<String, Object> results = new HashMap<>();
        List<Category> categoryFindAll = categoryRepository.findAll();
        if (!categoryFindAll.isEmpty()) {
            List<CategoryDTO> categoryDTOList = categoryFindAll.stream()
                    .map(category -> mapper.map(category, CategoryDTO.class))
                    .collect(Collectors.toList());
            // lấy ra những phần tử có cùng parentId
            Map<Long, List<CategoryDTO>> categoryMapChild = new HashMap<>();
            for (CategoryDTO category : categoryDTOList) {
                if (!categoryMapChild.containsKey(category.getParentId())) {
                    categoryMapChild.put(category.getParentId(), new ArrayList<>());
                }
                categoryMapChild.get(category.getParentId()).add(category);
            }
            results.put("child", categoryMapChild);
            results.put("category", categoryDTOList.stream().filter(category -> category.getParentId() == -1L).collect(Collectors.toList()));
        }
        return results;
    }
}
