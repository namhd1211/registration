package com.mitrais.registration.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = PhoneValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface PhoneConstraint {
    String message() default "USER0008";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
