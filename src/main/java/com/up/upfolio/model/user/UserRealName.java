package com.up.upfolio.model.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.up.upfolio.model.validators.BasicRequiredInputFieldConstraint;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

import static com.up.upfolio.model.validators.CommonValidationConfig.MAX_FIELD_LENGTH;

@Setter
@Getter
@AllArgsConstructor
@Embeddable
public class UserRealName implements Serializable {
    @JsonProperty(required = true)
    @BasicRequiredInputFieldConstraint(message = "Last name must not be empty")
    @Size(min = 1, max = MAX_FIELD_LENGTH)
    private String firstName;

    @JsonProperty(required = true)
    @BasicRequiredInputFieldConstraint(message = "Last name must not be empty")
    @Size(min = 1, max = MAX_FIELD_LENGTH)
    private String lastName;

    public UserRealName() {
    }
}
