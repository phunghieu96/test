package com.hieusd.productmanagement.model.response;

import com.hieusd.productmanagement.model.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@AllArgsConstructor
@Setter
public class CategoryPagingResponse {
    private int count;
    private int page;
    private int pageSize;
    private int pageTotal;
    private List<Category> categoryList;
}
