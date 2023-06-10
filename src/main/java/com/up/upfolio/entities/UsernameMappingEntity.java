package com.up.upfolio.entities;

import com.up.upfolio.model.user.UserType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "username_mapping")
@Getter
@Setter
public class UsernameMappingEntity {
    @Id
    private UUID userUuid;

    private UserType userType;

    @Column(unique = true, nullable = false)
    @NotBlank
    private String username;
}
