package com.hieusd.productmanagement.service;

import com.hieusd.productmanagement.model.entity.Category;
import com.hieusd.productmanagement.model.entity.Product;
import com.hieusd.productmanagement.model.response.ProductPagingResponse;

public interface CategoryService {
    ProductPagingResponse get();
    Category save(Category category);
    Category put (Category category);
    Category getById(Long id);
    void deleteById(Long id);
}
