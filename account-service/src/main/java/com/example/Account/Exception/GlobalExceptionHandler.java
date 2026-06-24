package com.example.Account.Exception;


import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

	
@ExceptionHandler(ResourceNotFoundException.class)
public ResponseEntity<String>
handleNotFound(ResourceNotFoundException ex) {

    return ResponseEntity
            .status(HttpStatus.NOT_FOUND)
            .body(ex.getMessage());
}

@ExceptionHandler(Exception.class)
public ResponseEntity<Map<String, String>>
handleException(Exception ex) {

    Map<String, String> error =
            new HashMap<>();

    error.put("message",
            ex.getMessage());

    error.put("status",
            "ERROR");

    return ResponseEntity
            .status(
                    HttpStatus.INTERNAL_SERVER_ERROR)
            .body(error);
}


}

