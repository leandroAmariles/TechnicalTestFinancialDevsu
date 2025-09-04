package com.devsu.customerservice.exeptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ServerWebExchange;

import java.util.Date;

@ControllerAdvice
public class GlobalHandlerException {

@ExceptionHandler(ClientException.class)
public ResponseEntity<ErrorMessage> handleClientException(ClientException e, ServerWebExchange exchange) {
    ErrorMessage errorMessage = ErrorMessage.builder()
            .status(400)
            .message(e.getMessage())
            .timestamp(String.valueOf(new Date()))
            .error("Error from client service")
            .path(exchange.getRequest().getPath().value())
            .build();

    return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
}

@ExceptionHandler(ResourceNotFoundException.class)
public ResponseEntity<ErrorMessage> handleResourceNotFoundException(ResourceNotFoundException e, ServerWebExchange exchange) {
    ErrorMessage errorMessage = ErrorMessage.builder()
            .status(404)
            .message(e.getMessage())
            .timestamp(String.valueOf(new Date()))
            .error("Resource Not Found")
            .path(exchange.getRequest().getPath().value())
            .build();

    return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
}

}
