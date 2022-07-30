package com.hieusd.productmanagement.controller;

import com.hieusd.productmanagement.model.dto.ProductDto;
import com.hieusd.productmanagement.model.entity.Category;
import com.hieusd.productmanagement.model.entity.Product;
import com.hieusd.productmanagement.model.response.CommonResponse;
import com.hieusd.productmanagement.model.response.ProductPagingResponse;
import com.hieusd.productmanagement.service.CategoryService;
import com.hieusd.productmanagement.service.ProductService;
import com.hieusd.productmanagement.utils.DtoToEntityConverter;
import com.hieusd.productmanagement.utils.EntityToDtoConverter;
import lombok.extern.log4j.Log4j2;
import net.kaczmarzyk.spring.data.jpa.domain.Equal;
import net.kaczmarzyk.spring.data.jpa.domain.GreaterThan;
import net.kaczmarzyk.spring.data.jpa.domain.LessThan;
import net.kaczmarzyk.spring.data.jpa.domain.Like;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Or;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@Log4j2
@RequestMapping(path = "api/v1/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private DtoToEntityConverter dtoToEntityConverter;

    @Autowired
    private EntityToDtoConverter entityToDtoConverter;

    @GetMapping("")
    public ResponseEntity<CommonResponse> get(
            @Or({
                    @Spec(path = "name", params = "name", spec = Like.class),
                    @Spec(path = "status", params = "status", spec = Equal.class),
                    @Spec(path = "description", params = "description", spec = Like.class),
                    @Spec(path = "categoryId", params = "categoryId", spec = Equal.class),
                    @Spec(path = "sku", params = "sku", spec = Equal.class),
                    @Spec(path = "price", params = "price", spec = Equal.class),
                    @Spec(path = "price", params = "price", spec = LessThan.class),
                    @Spec(path = "price", params = "price", spec = GreaterThan.class)
            }) Specification<Product> spec, @RequestHeader HttpHeaders headers, Sort sort
    ) {
        ProductPagingResponse pagingResponse = productService.get(spec, headers, sort);

        List<ProductDto> productList = entityToDtoConverter.convertToListProductDto(pagingResponse.getProducts());
        CommonResponse response = new CommonResponse(true, "Success", productList, null);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CommonResponse> getById(@PathVariable(value = "id") Long id) {
        ProductDto product = entityToDtoConverter.convertToDto(productService.getById(id));
        CommonResponse commonResponse = new CommonResponse(true, "success", product, null);
        return new ResponseEntity<>(commonResponse, HttpStatus.OK);
    }

    @PutMapping("")
    public ResponseEntity<CommonResponse> put(@Validated @RequestBody ProductDto productDto) {
        Category categoryEntity = categoryService.getById(productDto.getCategory().getId());
        //gọi getById Product để check xem có product với id trong body không nếu không thì throw not found
        Product productEntity = productService.getById(productDto.getId() != null ? productDto.getId() : 0);
        productEntity = dtoToEntityConverter.convertToEntity(productDto);
        //productEntity.setId(productDto.getId());
        //productEntity.setCategory(categoryEntity);

        ProductDto productDtoResponse = entityToDtoConverter.convertToDto(productService.put(productEntity));
        CommonResponse response = new CommonResponse(true, "Success", productDtoResponse, null);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<CommonResponse> save(@Validated @RequestBody ProductDto productDto) {
        Category category = categoryService.getById(productDto.getCategory().getId());
        Product product = dtoToEntityConverter.convertToEntity(productDto);
        product.setCategory(category);

        ProductDto productResponse = entityToDtoConverter.convertToDto(productService.save(product));
        CommonResponse commonResponse = new CommonResponse(true, "Success", productResponse, null);
        return new ResponseEntity<>(commonResponse, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CommonResponse> deleteById(@PathVariable(value = "id") Long id) {
        productService.deleteById(id);
        CommonResponse commonResponse = new CommonResponse(true, "Success", null, null);
        return new ResponseEntity<>(commonResponse, HttpStatus.ACCEPTED);
    }
}
