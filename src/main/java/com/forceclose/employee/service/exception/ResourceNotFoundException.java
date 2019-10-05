package com.forceclose.employee.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends BaseException{
    public ResourceNotFoundException(String msg) {
        super("02", msg, HttpStatus.NOT_FOUND);
    }
}
