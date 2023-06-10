package com.up.upfolio.model.validators;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class NullOrNotBlankValidator implements ConstraintValidator<NullOrNotBlankConstraint, String> {
    @Override
    public void initialize(NullOrNotBlankConstraint parameters) {

    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        return value == null || value.strip().length() > 0;
    }
}