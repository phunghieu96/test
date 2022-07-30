package com.hieusd.productmanagement.service.impl;

import com.hieusd.productmanagement.constants.CategoryStatus;
import com.hieusd.productmanagement.exception.NotFoundException;
import com.hieusd.productmanagement.model.entity.Category;
import com.hieusd.productmanagement.model.response.ProductPagingResponse;
import com.hieusd.productmanagement.repository.CategoryRepository;
import com.hieusd.productmanagement.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;


    @Override
    public ProductPagingResponse get() {
        return null;
    }

    @Override
    public Category save(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public Category put(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public Category getById(Long id) {
        return categoryRepository.findById(id).orElseThrow(() -> new NotFoundException("Cannot find by id"));
    }

    @Override
    public void deleteById(Long id) {
    Category category = categoryRepository.findById(id).orElseThrow(() -> new NotFoundException("Cannot find by id"));
    category.setStatus(CategoryStatus.INACTIVE);
    categoryRepository.save(category);
    }
}
