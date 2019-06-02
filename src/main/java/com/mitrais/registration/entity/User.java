package com.mitrais.registration.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
public class User {
    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String LastName;

    @Column(name = "phone", unique = true)
    private String phoneNumber;
    private LocalDate dob;
    private boolean gender;
    @Column(unique = true)
    private String email;
    @JsonIgnore
    @Column(name = "created_date")
    @CreationTimestamp
    private LocalDateTime createdDate;
    @JsonIgnore
    @Column(name = "last_updated")
    @UpdateTimestamp
    private LocalDateTime lastUpdated;
}