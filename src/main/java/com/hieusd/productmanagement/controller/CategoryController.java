package com.hieusd.productmanagement.controller;

import com.hieusd.productmanagement.model.dto.CategoryDto;
import com.hieusd.productmanagement.model.entity.Category;
import com.hieusd.productmanagement.model.response.CommonResponse;
import com.hieusd.productmanagement.service.CategoryService;
import com.hieusd.productmanagement.utils.DtoToEntityConverter;
import com.hieusd.productmanagement.utils.EntityToDtoConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/v1/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @Autowired
    private EntityToDtoConverter entityToDtoConverter;

    @Autowired
    private DtoToEntityConverter dtoToEntityConverter;

    @PostMapping("")
    public ResponseEntity<CommonResponse> create(@Validated @RequestBody CategoryDto categoryDto) {
        Category category = dtoToEntityConverter.convertToEntity(categoryDto);
        CategoryDto categoryResponse = entityToDtoConverter.convertToDto(categoryService.save(category));
        CommonResponse commonResponse = new CommonResponse(true, "Success", categoryResponse, null);
        return new ResponseEntity<>(commonResponse, HttpStatus.OK);
    }

}
