package com.anderson.hotel_reservation_system.entrypoint.exception;

import com.anderson.hotel_reservation_system.core.exceptions.DataConflictException;
import com.anderson.hotel_reservation_system.core.exceptions.InvalidDataException;
import com.anderson.hotel_reservation_system.core.exceptions.NotFoundException;
import com.anderson.hotel_reservation_system.core.exceptions.StandardException;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;

import static com.anderson.hotel_reservation_system.core.exceptions.constants.ExceptionConstants.AUTHENTICATION_FAILED;
import static com.anderson.hotel_reservation_system.core.exceptions.constants.ExceptionConstants.INTERNAL_SERVER_ERROR;

@RestControllerAdvice
public class ExceptionController {

    private static final Logger log = LoggerFactory.getLogger(ExceptionController.class);

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<StandardException> notFound(NotFoundException e, HttpServletRequest request) {
        log.warn("NotFoundException: {} at URI: {}", e.getMessage(), request.getRequestURI());
        StandardException exception = new StandardException(Instant.now(), HttpStatus.NOT_FOUND.value(), e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(exception.getStatus()).body(exception);
    }

    @ExceptionHandler(DataConflictException.class)
    public ResponseEntity<StandardException> dataConflict(DataConflictException e, HttpServletRequest request) {
        log.warn("DataConflictException: {} at URI: {}", e.getMessage(), request.getRequestURI());
        StandardException exception = new StandardException(Instant.now(), HttpStatus.CONFLICT.value(), e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(exception.getStatus()).body(exception);
    }

    @ExceptionHandler(InvalidDataException.class)
    public ResponseEntity<StandardException> invalidData(InvalidDataException e, HttpServletRequest request) {
        log.warn("InvalidDataException: {} at URI: {}", e.getMessage(), request.getRequestURI());
        StandardException exception = new StandardException(Instant.now(), HttpStatus.BAD_REQUEST.value(), e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(exception.getStatus()).body(exception);
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<StandardException> authenticationException(AuthenticationException e, HttpServletRequest request) {
        log.warn("AuthenticationException: {} at URI: {}", e.getMessage(), request.getRequestURI());
        StandardException exception = new StandardException(Instant.now(), HttpStatus.UNAUTHORIZED.value(), AUTHENTICATION_FAILED, request.getRequestURI());
        return ResponseEntity.status(exception.getStatus()).body(exception);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<StandardException> validationException(MethodArgumentNotValidException e, HttpServletRequest request) {
        String message = e.getBindingResult().getFieldError().getDefaultMessage();
        log.warn("MethodArgumentNotValidException: {} at URI: {}", message, request.getRequestURI());
        StandardException exception = new StandardException(Instant.now(), HttpStatus.BAD_REQUEST.value(), message, request.getRequestURI());
        return ResponseEntity.status(exception.getStatus()).body(exception);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<StandardException> genericException(Exception e, HttpServletRequest request) {
        log.error("Unexpected error: {} at URI: {}", e.getMessage(), request.getRequestURI(), e);
        StandardException exception = new StandardException(Instant.now(), HttpStatus.INTERNAL_SERVER_ERROR.value(), INTERNAL_SERVER_ERROR, request.getRequestURI());
        return ResponseEntity.status(exception.getStatus()).body(exception);
    }
}
