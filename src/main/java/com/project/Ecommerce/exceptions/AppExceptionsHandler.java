package com.project.Ecommerce.exceptions;

import java.util.Date;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class AppExceptionsHandler {

    @ExceptionHandler(value = {com.project.Ecommerce.exceptions.UserServiceException.class})
    public ResponseEntity<Object> handleUserServiceException(com.project.Ecommerce.exceptions.UserServiceException ex, WebRequest request) {

        com.project.Ecommerce.exceptions.ErrorMessage errorMessage = new com.project.Ecommerce.exceptions.ErrorMessage(new Date(), ex.getMessage());
        return new ResponseEntity<>(errorMessage, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<Object> handleOtherException(Exception ex, WebRequest request) {

        com.project.Ecommerce.exceptions.ErrorMessage errorMessage = new com.project.Ecommerce.exceptions.ErrorMessage(new Date(), ex.getMessage());
        return new ResponseEntity<>(errorMessage, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
