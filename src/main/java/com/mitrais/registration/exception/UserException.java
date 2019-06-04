package com.mitrais.registration.exception;

import com.mitrais.registration.model.ApiResponse;
import com.mitrais.registration.validation.ValidationUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Collections;
import java.util.List;

@ControllerAdvice
public class UserException {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse> invalidInput(MethodArgumentNotValidException ex) {
        BindingResult result = ex.getBindingResult();
        List<String> errors = ValidationUtil.fromBindingError(result);
        ApiResponse apiResponse = new ApiResponse(errors.get(0), Collections.emptyList());
        return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse> invalidInput(Exception ex) {
        ApiResponse apiResponse = new ApiResponse(ex.getMessage(), Collections.emptyList());
        return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
    }
}
