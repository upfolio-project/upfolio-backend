package com.up.upfolio.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;

@Entity
@Table(name="refresh_tokens")
@Getter
@Setter
public class JwtRefreshToken {
    @Id
    private String tokenHash;

    @ManyToOne
    @JoinColumn(name = "user_uuid")
    private User user;

    private OffsetDateTime created;

    @NotNull
    private Integer usageCount = 0;
}
