package com.hieusd.productmanagement.service;

import com.hieusd.productmanagement.model.entity.Product;
import com.hieusd.productmanagement.model.response.ProductPagingResponse;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpHeaders;

public interface ProductService {
    ProductPagingResponse get(Specification<Product> spec, HttpHeaders headers, Sort sort);
    Product getById(Long id);
    Product save(Product product);
    Product put(Product product);
    void deleteById(Long id);

}
