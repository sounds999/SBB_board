package com.example.demo;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "에러다 돌아가라")
public class DataNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L; // 해당 변수가 필요한 이유?

    public DataNotFoundException(String message) {
        super(message);
    }
}
