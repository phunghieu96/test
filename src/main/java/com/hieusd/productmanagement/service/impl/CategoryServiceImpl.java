package com.hieusd.productmanagement.service.impl;

import com.hieusd.productmanagement.constants.CategoryStatus;
import com.hieusd.productmanagement.exception.NotFoundException;
import com.hieusd.productmanagement.model.entity.Category;
import com.hieusd.productmanagement.model.response.CategoryPagingResponse;
import com.hieusd.productmanagement.model.response.ProductPagingResponse;
import com.hieusd.productmanagement.repository.CategoryRepository;
import com.hieusd.productmanagement.service.CategoryService;
import com.hieusd.productmanagement.utils.PaginationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;


    @Override
    public CategoryPagingResponse get(Specification<Category> spec, HttpHeaders headers, Sort sort) {
        if(PaginationUtils.isPaginationRequested(headers)){
            return helperGet(spec, PaginationUtils.buildPageRequest(headers, sort));
        }else{
                List<Category> categories = helperGet(spec, sort);
                return new CategoryPagingResponse(categories.size(), 0, 0, 0, categories);
        }
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

    @Override
    public void insert() {
        Category category = new Category();
        for (int i = 0; i < 1000; i++){
            category.setId((long) (i + 5));
            category.setStatus(CategoryStatus.ACTIVE);
            category.setCode("aaa");
            category.setDescription("good");
            category.setName("stno1 " + i);
            categoryRepository.save(category);
        }
    }

    /**
     * helper methods
     */
    public List<Category> helperGet(Specification<Category> spec, Sort sort) {
        return categoryRepository.findAll(spec, sort);
    }

    public CategoryPagingResponse helperGet(Specification<Category> spec, Pageable pageable) {
        Page<Category> page = categoryRepository.findAll(spec, pageable);
        List<Category> categories = page.getContent();
        return new CategoryPagingResponse((int) page.getTotalElements(), page.getNumber(), page.getNumberOfElements(), page.getTotalPages(), categories);
    }
}
