package com.forceclose.employee.service.exception;

import org.springframework.http.HttpStatus;

public class BadRequestException extends BaseException{
    public BadRequestException(String message) {
        super("01", message, HttpStatus.BAD_REQUEST);
    }
}
