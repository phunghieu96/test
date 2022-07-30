package com.hieusd.productmanagement.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum WareHouseStatus {
    ACTIVE(1),
    INACTIVE(0);
    private final int status;
}
