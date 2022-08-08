package com.hieusd.productmanagement.controller;

import com.hieusd.productmanagement.constants.CategoryStatus;
import com.hieusd.productmanagement.model.dto.CategoryDto;
import com.hieusd.productmanagement.model.entity.Category;
import com.hieusd.productmanagement.model.response.CategoryPagingResponse;
import com.hieusd.productmanagement.model.response.CommonResponse;
import com.hieusd.productmanagement.repository.CategoryRepository;
import com.hieusd.productmanagement.service.CategoryService;
import com.hieusd.productmanagement.utils.DtoToEntityConverter;
import com.hieusd.productmanagement.utils.EntityToDtoConverter;
import net.kaczmarzyk.spring.data.jpa.domain.Like;
import net.kaczmarzyk.spring.data.jpa.web.annotation.And;
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
@RequestMapping(path = "api/v1/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @Autowired
    private EntityToDtoConverter entityToDtoConverter;

    @Autowired
    private DtoToEntityConverter dtoToEntityConverter;

    @GetMapping("")
    public ResponseEntity<CommonResponse> get(
            @And({
                    @Spec(path = "name", params = "name", spec = Like.class)
            }) Specification<Category> spec, @RequestHeader HttpHeaders headers, Sort sort
    ){
        CategoryPagingResponse pagingResponse = categoryService.get(spec, headers, sort);
        List<CategoryDto> categoryDtoList = entityToDtoConverter.convertToListCategoryDto(pagingResponse.getCategoryList());
        CommonResponse commonResponse = new CommonResponse(true, "Success", categoryDtoList, null);
        return new ResponseEntity<>(commonResponse, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<CommonResponse> create(@Validated @RequestBody CategoryDto categoryDto) {
        Category category = dtoToEntityConverter.convertToEntity(categoryDto);
        CategoryDto categoryResponse = entityToDtoConverter.convertToDto(categoryService.save(category));
        CommonResponse response = new CommonResponse(true, "Success",  categoryResponse, null);
        return new ResponseEntity<>(response,HttpStatus.CREATED);
    }

    @PutMapping("")
    public ResponseEntity<CommonResponse> update(@Validated @RequestBody CategoryDto categoryDto){
        Category category = categoryService.getById(categoryDto.getId());
         category = dtoToEntityConverter.convertToEntity(categoryDto);
         CategoryDto categoryRes = entityToDtoConverter.convertToDto(categoryService.put(category));
         CommonResponse response = new CommonResponse(true, "Success", categoryRes, null);
         return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity<CommonResponse> getById(@PathVariable(value = "id") Long id){
        CategoryDto categoryDto = entityToDtoConverter.convertToDto(categoryService.getById(id));
        CommonResponse commonResponse = new CommonResponse(true, "Success", categoryDto, null);
        return new ResponseEntity<>(commonResponse, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CommonResponse> deleteById(@PathVariable(value = "id") Long id) {
       categoryService.deleteById(id);
       CommonResponse commonResponse = new CommonResponse(true, "Success", null, null);
       return new ResponseEntity<>(commonResponse, HttpStatus.ACCEPTED);
    }

    @PostMapping("insert")
    public ResponseEntity<CommonResponse> insert(){
        categoryService.insert();
        CommonResponse commonResponse = new CommonResponse(true, "success", null, null);
        return new ResponseEntity<>(commonResponse, HttpStatus.OK);
    }
}
