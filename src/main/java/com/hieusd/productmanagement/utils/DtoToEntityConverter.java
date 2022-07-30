package com.hieusd.productmanagement.utils;

import com.hieusd.productmanagement.model.dto.CategoryDto;
import com.hieusd.productmanagement.model.dto.ProductDto;
import com.hieusd.productmanagement.model.entity.Category;
import com.hieusd.productmanagement.model.entity.Product;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.expression.ParseException;

@Component
public class DtoToEntityConverter {

    @Autowired
    private ModelMapper modelMapper;

    public Product convertToEntity(ProductDto productDto) throws ParseException {
        return modelMapper.map(productDto, Product.class);
    }

    public Category convertToEntity(CategoryDto categoryDto) throws ParseException {
        return modelMapper.map(categoryDto, Category.class);
    }
}
