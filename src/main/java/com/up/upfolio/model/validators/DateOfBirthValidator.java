package com.up.upfolio.model.validators;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;

public class DateOfBirthValidator implements ConstraintValidator<DateOfBirthConstraint, LocalDate> {
    public void initialize(NullOrNotBlank parameters) {

    }

    public boolean isValid(LocalDate value, ConstraintValidatorContext constraintValidatorContext) {
        return value == null || (value.isBefore(LocalDate.now().minusYears(10)) && value.isAfter(LocalDate.now().minusYears(100)));
    }
}
