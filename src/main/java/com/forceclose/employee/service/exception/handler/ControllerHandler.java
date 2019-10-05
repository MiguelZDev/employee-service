package com.forceclose.employee.service.exception.handler;


import com.forceclose.employee.service.exception.BaseException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class ControllerHandler {

    @ExceptionHandler(BaseException.class)
    public HttpEntity<BaseException> handleBaseException(BaseException baseException) {
        log.warn("An exception ocurred: {}", baseException);
        return new ResponseEntity<>(baseException, baseException.getHttpStatus());
    }
}
