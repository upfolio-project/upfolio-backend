package com.up.upfolio.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "users")
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID uuid;

    @NotEmpty(message = "Phone number must be set")
    @Size(min = 6, max = 11, message = "Phone number must be between 2 and 32 characters long")
    private String phoneNumber;

    @NotNull(message = "Username must not be blank")
    private UserRealNameModel name;

    @JsonIgnore
    private String passwordHash;
}