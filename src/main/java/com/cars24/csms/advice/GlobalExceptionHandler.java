package com.cars24.csms.advice;

import com.cars24.csms.data.res.ApiResponse;
import com.cars24.csms.exceptions.UserServiceException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public  ResponseEntity<ApiResponse> handleValidationExceptions(MethodArgumentNotValidException exception)
    {
        log.info("[handleValidationExceptions]");
        Map<String,String> errorMap=new HashMap<>();
        exception.getBindingResult().getFieldErrors().forEach(error->
        {
            errorMap.put(error.getField(),error.getDefaultMessage());
        });

        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setStatus(HttpStatus.BAD_REQUEST.value());
        apiResponse.setSuccess(false);
        apiResponse.setMessage("ERROR: INVALID DATA!");
        apiResponse.setData(errorMap);
        apiResponse.setService("APP_USER - " + HttpStatus.BAD_REQUEST.value());
        return ResponseEntity.badRequest().body(apiResponse);

    }

    @ExceptionHandler(UserServiceException.class)
    public  ResponseEntity<ApiResponse> handleUserServiceExceptions(UserServiceException exception)
    {
        log.info("[handleUserServiceExceptions]");
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setStatus(HttpStatus.BAD_REQUEST.value());
        apiResponse.setSuccess(false);
        apiResponse.setMessage(exception.getMessage());
        apiResponse.setData(null);
        apiResponse.setService("APP_USER - " + HttpStatus.BAD_REQUEST.value());
        return ResponseEntity.badRequest().body(apiResponse);

    }

}