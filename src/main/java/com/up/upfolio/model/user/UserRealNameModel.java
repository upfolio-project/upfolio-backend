package com.up.upfolio.model.user;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
public class UserRealNameModel {
    private static final int MAX_FIELD_LENGTH = 30;

    private String firstName;
    private String middleName;
    private String lastName;

    public boolean isValid() {
        if (firstName == null)
            return false;

        String stripped = firstName.strip();
        if (stripped.isEmpty() || stripped.length() > MAX_FIELD_LENGTH)
            return false;

        return fieldLengthLessOrNull(middleName) && fieldLengthLessOrNull(lastName);
    }

    private boolean fieldLengthLessOrNull(String s) {
        if (s == null)
            return true;

        return s.length() <= MAX_FIELD_LENGTH;
    }
}
