package com.example.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND,reason = "Courrier not found!")
public class CourrierNotFoundException extends RuntimeException {

    public CourrierNotFoundException(String msg) {
        super(msg);
    }
}
