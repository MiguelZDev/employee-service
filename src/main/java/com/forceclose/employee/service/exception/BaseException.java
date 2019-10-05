package com.forceclose.employee.service.exception;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({ "cause", "stackTrace", "suppressed", "localizedMessage", "httpStatus" })
public class BaseException extends RuntimeException{
    private static final long serialVersionUID = 1412579419703807855L;
    private String code;
    private String message;
    private HttpStatus httpStatus;
}
