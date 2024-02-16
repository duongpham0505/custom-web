package com.dev.custom.repository;

import com.dev.custom.service.data.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Category findById(long categoryId);

    List<Category> findAllByParentId(long parentId);
}
