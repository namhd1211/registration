package com.mitrais.registration.validation;

import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;

import java.util.ArrayList;
import java.util.List;

public class ValidationUtil {
    public static List<String> fromBindingError(Errors errors) {
        List<String> validErrors = new ArrayList<>();
        for (ObjectError objError : errors.getAllErrors()) {
            validErrors.add(objError.getDefaultMessage());
        }
        return validErrors;
    }
}
