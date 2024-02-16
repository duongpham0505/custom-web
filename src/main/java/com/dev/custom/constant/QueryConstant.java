package com.dev.custom.constant;

public class QueryConstant {
    private QueryConstant() {
    }
    public static final String QUERY_COUNT_PRODUCT = "SELECT count(*) from Product";
    public static final String SELECT_LIKE_PRODUCT_NAME = "SELECT * FROM Product WHERE productName = :productName";
}
