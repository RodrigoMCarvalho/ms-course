package com.rodrigo.hroauth.exceptions;

import feign.FeignException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class AppExceptionHandler {

    @ExceptionHandler(EmailNotFountException.class)
    public ResponseEntity<?> handlerEmailNotFountException(EmailNotFountException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(FeignException.NotFound.class)
    public ResponseEntity<?> handlerFeignExceptionNotFount(FeignException e) {
        return new ResponseEntity<>("Recurso não encontrado", HttpStatus.NOT_FOUND);
    }

}
