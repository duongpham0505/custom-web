package com.dev.custom.service.data.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class RoleDTO {
    private long id;
    private String roleName;
    private String roleCode;
    private String description;
}
