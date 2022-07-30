package com.hieusd.productmanagement.model.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class CommonResponse {
    private boolean success;
    private String message;
    private Object data;
    private Object error;
}
