package com.hieusd.productmanagement.exception;

import com.hieusd.productmanagement.model.response.CommonResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(value = {BadRequestException.class})
    public ResponseEntity<Object> handleBadRequestException(BadRequestException e) {
        e.printStackTrace();
        List<String> errors = new ArrayList<>();
        errors.add(e.getLocalizedMessage());
        CommonResponse commonResponse = new CommonResponse(false, "BadRequest", null, errors);
        return new ResponseEntity<>(commonResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {NotFoundException.class})
    public ResponseEntity<Object> handleNotFoundException(NotFoundException e) {
        e.printStackTrace();
        List<String> errors = new ArrayList<>();
        errors.add(e.getLocalizedMessage());
        CommonResponse commonResponse = new CommonResponse(false, "Not Found", null, errors);
        return new ResponseEntity<>(commonResponse, HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<Object> handleMethodArgumentNotValidException(MethodArgumentNotValidException e, HttpHeaders headers, HttpStatus status, WebRequest request) {
        e.printStackTrace();
        List<String> errors = new ArrayList<>();
        for (ObjectError error : e.getBindingResult().getAllErrors()
        ) {
            errors.add(error.getDefaultMessage());
        }
        CommonResponse commonResponse = new CommonResponse(false, "Data validate error!!!", null, errors);
        return new ResponseEntity<>(commonResponse, HttpStatus.BAD_REQUEST);
    }

    /*
    Global exception handler
    */
    @ExceptionHandler({Exception.class})
    public ResponseEntity<Object> handleAllException(Exception e) {
    e.printStackTrace();
    List<String> errors = new ArrayList<>();
    errors.add(e.getLocalizedMessage());
    CommonResponse commonResponse = new CommonResponse(false, "Error occured :(((", null, errors);
    return new ResponseEntity<>(commonResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
