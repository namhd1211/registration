package com.mitrais.registration.model;

import com.mitrais.registration.validation.PhoneConstraint;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.time.LocalDate;

@Data
public class UserRequest {
    @NotNull(message = "First name is required")
    private String firstName;
    @NotNull(message = "Last name is required")
    private String lastName;

    @NotNull(message = "Mobile number is required.")
    @PhoneConstraint
    private String phoneNumber;
    @Past
    private LocalDate dob;

    private boolean gender;

    @NotNull(message = "Email is required")
    @Email(message = "Email is invalid")
    private String email;
}
