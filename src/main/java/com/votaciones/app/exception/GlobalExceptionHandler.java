package com.votaciones.app.exception;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;
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

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    private static final String TIMESTAMP = "timestamp";
    private static final String STATUS = "status";
    private static final String ERROR = "error";
    private static final String MESSAGE = "message";
    private static final String PATH = "path";
    private static final String BAD_REQUEST = "Bad Request";
    private static final String INTERNAL_SERVER_ERROR = "Internal Server Error";
    private static final String RESOURCE_NOT_FOUND = "Resource Not Found";

    @ExceptionHandler
    public ResponseEntity<Map<String, Object>> handleResourceNotFound(ResourceNotFoundException ex){
        Map<String, Object> error = new HashMap<>();
        error.put(TIMESTAMP, LocalDateTime.now());
        error.put(STATUS, HttpStatus.NOT_FOUND.value());
        error.put(ERROR, RESOURCE_NOT_FOUND);
        error.put(MESSAGE, ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler
    public ResponseEntity<Map<String, Object>> handlerBadRequest(BadRequestException ex){
        Map<String, Object> error = new HashMap<>();
        error.put(TIMESTAMP, LocalDateTime.now());
        error.put(STATUS, HttpStatus.BAD_REQUEST.value());
        error.put(ERROR, BAD_REQUEST);
        error.put(MESSAGE, ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleGeneralException(Exception ex){
        Map<String, Object> error = new HashMap<>();
        error.put(TIMESTAMP, LocalDateTime.now());
        error.put(STATUS, HttpStatus.INTERNAL_SERVER_ERROR.value());
        error.put(ERROR, INTERNAL_SERVER_ERROR);
        error.put(MESSAGE, ex.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }


    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, WebRequest request){
        List<Map<String,String>> fieldError = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map( fe -> {
                    Map<String, String> m = new HashMap<>();
                    m.put("field", fe.getField());
                    m.put("rejectedValue", String.valueOf(fe.getRejectedValue()));
                    m.put(MESSAGE, fe.getDefaultMessage());
                    return m;
                })
                .toList();

        Map<String, Object> body = new LinkedHashMap<>();
        body.put(TIMESTAMP, LocalDateTime.now());
        body.put(STATUS, HttpStatus.BAD_REQUEST.value());
        body.put(ERROR, BAD_REQUEST);
        body.put(MESSAGE, "Validation failed");
        body.put(PATH, request.getDescription(false).replace("uri=", ""));
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
                    m.put(MESSAGE, v.getMessage());
                    return m;
                }).toList();

        Map<String, Object> body = new LinkedHashMap<>();
        body.put(TIMESTAMP, LocalDateTime.now());
        body.put(STATUS, HttpStatus.BAD_REQUEST.value());
        body.put(ERROR, BAD_REQUEST);
        body.put(MESSAGE, "Parameter validation failed");
        body.put(PATH, request.getRequestURI());
        body.put("violations", violations);

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);

    }


    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, WebRequest request){

        Map<String, Object> body = new LinkedHashMap<>();
        body.put(TIMESTAMP, LocalDateTime.now());
        body.put(STATUS, HttpStatus.BAD_REQUEST.value());
        body.put(ERROR, BAD_REQUEST);
        body.put(MESSAGE, "Malformed JSON request" + Optional.ofNullable(ex.getMostSpecificCause()).map(Throwable::getMessage).orElse(ex.getMessage()));
        body.put(PATH, request.getDescription(false).replace("uri", ""));
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);


    }




}
