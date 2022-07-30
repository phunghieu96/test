package com.hieusd.productmanagement.model.dto;

import com.hieusd.productmanagement.constants.CategoryStatus;
import lombok.Data;


@Data
public class CategoryDto {
    private Long id;

    private String name;

    private String code;

    private String description;

    private CategoryStatus status;
}
