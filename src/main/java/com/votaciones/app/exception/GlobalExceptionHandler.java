package com.votaciones.app.exception;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<?> handleResourceNotFound(ResourceNotFoundException ex){
        Map<String, Object> error = new HashMap<>();
        error.put("timestamp", LocalDateTime.now());
        error.put("status", HttpStatus.NOT_FOUND.value());
        error.put("error", "Resource Not Found");
        error.put("message", ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler
    public ResponseEntity<?> handlerBadRequest(BadRequestException ex){
        Map<String, Object> error = new HashMap<>();
        error.put("timestamp", LocalDateTime.now());
        error.put("status", HttpStatus.BAD_REQUEST.value());
        error.put("error", "Bad Request");
        error.put("message", ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleGeneralException(Exception ex){
        Map<String, Object> error = new HashMap<>();
        error.put("timestamp", LocalDateTime.now());
        error.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
        error.put("error", "Internal Server Error");
        error.put("message", ex.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }


    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request){
        List<Map<String,String>> fieldError = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map( fe -> {
                    Map<String, String> m = new HashMap<>();
                    m.put("field", fe.getField());
                    m.put("rejectedValue", String.valueOf(fe.getRejectedValue()));
                    m.put("message", fe.getDefaultMessage());
                    return m;
                })
                .collect(Collectors.toList());

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("status", HttpStatus.BAD_REQUEST.value());
        body.put("error", "Bad Request");
        body.put("message", "Validation failed");
        body.put("path", request.getDescription(false).replace("uri=", ""));
        body.put("fieldErrors", fieldError);

        return new ResponseEntity<>(body,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Object> handleConstraintViolation (ConstraintViolationException ex, HttpServletRequest request){
        List<Map<String,String>> violations = ex.getConstraintViolations()
                .stream()
                .map(v -> {
                    Map<String, String> m = new HashMap<>();
                    String path = "";
                    if(v.getPropertyPath() != null) path = v.getPropertyPath().toString();
                    m.put("property", path);
                    m.put("invalidValue", String.valueOf(v.getInvalidValue()));
                    m.put("message", v.getMessage());
                    return m;
                }).collect(Collectors.toList());

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("status", HttpStatus.BAD_REQUEST.value());
        body.put("error", "Bad Request");
        body.put("message", "Parameter validation failed");
        body.put("path", request.getRequestURI());
        body.put("violations", violations);

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);

    }


    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request){

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("status", HttpStatus.BAD_REQUEST.value());
        body.put("error", "Bad Request");
        body.put("message", "Malformed JSON request" + Optional.ofNullable(ex.getMostSpecificCause()).map(Throwable::getMessage).orElse(ex.getMessage()));
        body.put("path", request.getDescription(false).replace("uri", ""));
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);


    }




}
