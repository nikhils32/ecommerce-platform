package com.productservice.productservice.controller;

import com.productservice.productservice.dto.ExceptionDTO;
import com.productservice.productservice.exception.ProductException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ProductControllerAdvices {
//    @ExceptionHandler
//    private ResponseEntity<ExceptionDTO> handleProductException(ProductException e) {
//        ExceptionDTO exceptionDTO = new ExceptionDTO();
//        exceptionDTO.setHttpStatus(HttpStatus.NOT_FOUND);
//        exceptionDTO.setMessage(e.getMessage());
//        //return exceptionDTO;
//        return new ResponseEntity<>(
//                exceptionDTO,
//                HttpStatus.NOT_FOUND
//        );
//    }
@ExceptionHandler
@ResponseStatus(HttpStatus.NOT_FOUND)
private ExceptionDTO handleProductException(ProductException e) {
    ExceptionDTO exceptionDTO = new ExceptionDTO();
    exceptionDTO.setHttpStatus(HttpStatus.NOT_FOUND);
    exceptionDTO.setMessage(e.getMessage());
    return exceptionDTO;
}
}
