package com.hieusd.productmanagement.service;

import com.hieusd.productmanagement.model.entity.Category;
import com.hieusd.productmanagement.model.response.CategoryPagingResponse;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpHeaders;

public interface CategoryService {
    CategoryPagingResponse get(Specification<Category> spec, HttpHeaders headers, Sort sort);
    Category save(Category category);
    Category put (Category category);
    Category getById(Long id);
    void deleteById(Long id);
    void insert();
}
