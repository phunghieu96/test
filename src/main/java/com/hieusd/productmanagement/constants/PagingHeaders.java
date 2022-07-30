package com.hieusd.productmanagement.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor
public enum PagingHeaders {
    PAGE_SIZE("page_size"),
    PAGE("page"),
    PAGE_TOTAL("page_total"),
    COUNT("count");
    private final String name;
}
