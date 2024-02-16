package com.dev.custom.service.data.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class Response<T> {
    private String code;
    private String message;
    private T object;
}
