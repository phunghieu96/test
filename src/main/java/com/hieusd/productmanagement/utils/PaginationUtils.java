package com.hieusd.productmanagement.utils;

import com.hieusd.productmanagement.constants.PagingHeaders;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;

import java.util.Objects;

public class PaginationUtils {
    public static boolean isPaginationRequested(HttpHeaders headers) {
        return headers.containsKey(PagingHeaders.PAGE.getName()) && headers.containsKey(PagingHeaders.PAGE_SIZE.getName());
    }

    public static Pageable buildPageRequest(HttpHeaders headers, Sort sort) {
        int page = Integer.parseInt(Objects.requireNonNull(headers.get(PagingHeaders.PAGE.getName())).get(0));
        int size = Integer.parseInt(Objects.requireNonNull(headers.get(PagingHeaders.PAGE_SIZE.getName())).get(0));
        return PageRequest.of(page, size, sort);
    }
}