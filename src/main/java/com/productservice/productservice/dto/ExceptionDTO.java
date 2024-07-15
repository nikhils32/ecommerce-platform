package com.productservice.productservice.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Setter
@Getter
public class ExceptionDTO {
    private HttpStatus httpStatus;
    private String message;
}
