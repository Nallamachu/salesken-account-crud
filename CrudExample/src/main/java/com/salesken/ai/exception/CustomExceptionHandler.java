package com.salesken.ai.exception;

import com.salesken.ai.controller.AccountController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice(assignableTypes = AccountController.class)
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    protected ResponseEntity<Object> handlException(RuntimeException ex){
        Error error = Error.builder(HttpStatus.BAD_REQUEST.value(),ex.getLocalizedMessage());
        return new ResponseEntity(error, HttpStatus.BAD_REQUEST);
    }

}
