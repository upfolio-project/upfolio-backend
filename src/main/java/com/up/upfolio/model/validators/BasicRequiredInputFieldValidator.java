package com.up.upfolio.model.validators;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class BasicRequiredInputFieldValidator implements ConstraintValidator<BasicRequiredInputFieldConstraint, String> {
    @Override
    public void initialize(BasicRequiredInputFieldConstraint parameters) {

    }
    
    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return s != null && s.strip().length() != 0;
    }
}
