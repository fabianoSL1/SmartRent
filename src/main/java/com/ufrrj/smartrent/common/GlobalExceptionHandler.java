package com.ufrrj.smartrent.common;

import com.ufrrj.smartrent.common.exception.DomainException;
import com.ufrrj.smartrent.common.exception.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @Value("${api.callstack}")
    private boolean callstack;

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGenericException(Exception exception) {
        logging(exception);

        return new ResponseEntity<>("An error occurred", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(DomainException.class)
    public ResponseEntity<Map<String, String>> handleDomainException(RuntimeException exception) {
        logging(exception);

        Map<String, String> response = new HashMap<>();
        response.put("message", exception.getMessage());

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Map<String, String>> handleNotFoundException(RuntimeException exception) {
        logging(exception);

        Map<String, String> response = new HashMap<>();
        response.put("message", exception.getMessage());

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    private void logging(Exception exception) {
        log.error(exception.getMessage());

        if (callstack) {
            log.error(exception.getMessage(), exception);
        }
    }

}
