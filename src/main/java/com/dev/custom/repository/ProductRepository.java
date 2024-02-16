package com.dev.custom.repository;

import com.dev.custom.constant.QueryConstant;
import com.dev.custom.service.data.entity.Product;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query(QueryConstant.QUERY_COUNT_PRODUCT)
    int total();

    List<Product> findAllByCategoryId(long categoryId, Pageable pageable);

//    @Query(QueryConstant.SELECT_LIKE_PRODUCT_NAME)
//    List<Product> searchProduct(String productName);

}
