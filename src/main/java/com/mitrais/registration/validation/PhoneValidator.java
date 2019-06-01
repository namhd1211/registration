package com.mitrais.registration.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PhoneValidator implements ConstraintValidator<PhoneConstraint, String> {
   public void initialize(PhoneConstraint constraint) {
   }

   public boolean isValid(String value, ConstraintValidatorContext context) {
      return value.matches("^(0)?[1-9]\\d{8}$");
   }
}
