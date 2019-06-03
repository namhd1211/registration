package com.mitrais.registration.model;

import com.mitrais.registration.validation.PhoneConstraint;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Past;
import java.time.LocalDate;

@Data
public class UserRequest {
    public static final String FIRST_NAME_IS_REQUIRED = "USER0003";
    public static final String LAST_NAME_IS_REQUIRED = "USER0004";
    public static final String PHONE_IS_REQUIRED = "USER0005";
    public static final String EMAIL_IS_REQUIRED = "USER0006";
    public static final String INVALID_EMAIL = "USER0007";

    @NotEmpty(message = FIRST_NAME_IS_REQUIRED)
    private String firstName;
    @NotEmpty(message = LAST_NAME_IS_REQUIRED)
    private String lastName;

    @NotEmpty(message = PHONE_IS_REQUIRED)
    @PhoneConstraint
    private String phoneNumber;
    @Past
    private LocalDate dob;

    private Gender gender;

    @NotEmpty(message = EMAIL_IS_REQUIRED)
    @Email(message = INVALID_EMAIL)
    private String email;
}
