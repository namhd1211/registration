package com.mitrais.registration.model;

import com.mitrais.registration.validation.PhoneConstraint;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Past;
import java.time.LocalDate;

@Data
public class UserRequest {
    @NotEmpty(message = "First name is required")
    private String firstName;
    @NotEmpty(message = "Last name is required")
    private String lastName;

    @NotEmpty(message = "Phone number is required.")
    @PhoneConstraint
    private String phoneNumber;
    @Past
    private LocalDate dob;

    private Gender gender;

    @NotEmpty(message = "Email is required")
    @Email(message = "Email is invalid")
    private String email;
}
