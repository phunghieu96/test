package com.hieusd.productmanagement.service.impl;

import com.hieusd.productmanagement.constants.ProductStatus;
import com.hieusd.productmanagement.exception.NotFoundException;
import com.hieusd.productmanagement.model.entity.Product;
import com.hieusd.productmanagement.model.response.ProductPagingResponse;
import com.hieusd.productmanagement.repository.ProductRepository;
import com.hieusd.productmanagement.service.ProductService;
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
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public ProductPagingResponse get(Specification<Product> spec, HttpHeaders headers, Sort sort) {
        if (PaginationUtils.isPaginationRequested(headers)) {
            return helperGet(spec, PaginationUtils.buildPageRequest(headers, sort));
        } else {
            List<Product> products = helperGet(spec, sort);
            return new ProductPagingResponse(products.size(), 0, 0,0, products);
        }
    }

    @Override
    public Product getById(Long id) {
        return productRepository.findById(id).orElseThrow(() -> new NotFoundException("Cannot find by Id"));
    }

    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product put(Product product) {
        return productRepository.save(product);
    }

    @Override
    public void deleteById(Long id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new NotFoundException("Cannot find by Id"));
        product.setStatus(ProductStatus.INACTIVE);
        productRepository.save(product);
    }

    /**
     * helper methods
     */
    public List<Product> helperGet(Specification<Product> spec, Sort sort) {
        return productRepository.findAll(spec, sort);
    }

    public ProductPagingResponse helperGet(Specification<Product> spec, Pageable pageable) {
        Page<Product> page = productRepository.findAll(spec, pageable);
        List<Product> productEntities = page.getContent();
        return new ProductPagingResponse((int) page.getTotalElements(), page.getNumber(), page.getNumberOfElements(), page.getTotalPages(), productEntities);
    }
}
