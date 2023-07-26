package com.example.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND,reason = "Restaurant not found!")
public class RestaurantNotFoundException extends RuntimeException{
    public RestaurantNotFoundException(String msg) {
        super(msg);
    }
}
