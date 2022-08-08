package com.hieusd.productmanagement.utils;

import com.hieusd.productmanagement.model.dto.CategoryDto;
import com.hieusd.productmanagement.model.dto.ProductDto;
import com.hieusd.productmanagement.model.entity.Category;
import com.hieusd.productmanagement.model.entity.Product;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class EntityToDtoConverter {
    @Autowired
    private ModelMapper modelMapper;

    public ProductDto convertToDto(Product product) {

        return modelMapper.map(product, ProductDto.class);
    }

    public List<ProductDto> convertToListProductDto(List<Product> productEntity) {
        List<ProductDto> listProductDto = new ArrayList<>();
        for (Product product : productEntity) {
            listProductDto.add(modelMapper.map(product, ProductDto.class));
        }
        return listProductDto;
    }

    public CategoryDto convertToDto(Category category) {

        return modelMapper.map(category, CategoryDto.class);
    }

    public List<CategoryDto> convertToListCategoryDto(List<Category> categories) {
        List<CategoryDto> listCategoryDto = new ArrayList<>();
        for (Category category : categories) {
            listCategoryDto.add(modelMapper.map(category, CategoryDto.class));
        }
        return listCategoryDto;
    }
}
