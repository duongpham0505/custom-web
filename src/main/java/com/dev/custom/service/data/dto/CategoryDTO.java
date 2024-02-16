package com.dev.custom.service.data.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryDTO {
    private long id;
    private String categoryName;
    private String description;
    private long parentId;
    private int orderCategory;
    private long createTime;
    private boolean inActive;
}
