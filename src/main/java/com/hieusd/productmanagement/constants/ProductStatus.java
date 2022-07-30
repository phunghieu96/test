package com.hieusd.productmanagement.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ProductStatus {
    ACTIVE(1),
    INACTIVE(0);
    private final int status;
}
