package com.up.upfolio.entities;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@Embeddable
public class UserRealNameModel {
    private static final int MAX_FIELD_LENGTH = 30;

    @NotBlank(message = "First name must be non-empty")
    private String firstName;

    @NotBlank(message = "Last name must be non-empty")
    private String lastName;

    public UserRealNameModel() {
    }

    public boolean checkValid() {
        if (firstName == null)
            return false;

        String stripped = firstName.strip();
        if (stripped.isEmpty() || stripped.length() > MAX_FIELD_LENGTH)
            return false;

        return fieldLengthLessOrNull(lastName);
    }

    private boolean fieldLengthLessOrNull(String s) {
        if (s == null)
            return true;

        return s.length() <= MAX_FIELD_LENGTH;
    }
}
