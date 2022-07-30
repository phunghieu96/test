package com.hieusd.productmanagement.utils;

import com.hieusd.productmanagement.constants.PagingHeaders;
import com.hieusd.productmanagement.model.response.ProductPagingResponse;
import org.springframework.http.HttpHeaders;

public class HttpHeadersUtils {
    public static HttpHeaders returnHttpHeaders(ProductPagingResponse response) {
        HttpHeaders headers = new HttpHeaders();
        headers.set(PagingHeaders.COUNT.getName(), String.valueOf(response.getCount()));
        headers.set(PagingHeaders.PAGE.getName(), String.valueOf(response.getPage()));
        headers.set(PagingHeaders.PAGE_SIZE.getName(), String.valueOf(response.getPageSize()));
        headers.set(PagingHeaders.PAGE_TOTAL.getName(), String.valueOf(response.getPageTotal()));
        return headers;
    }
}
