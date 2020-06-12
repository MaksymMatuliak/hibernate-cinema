package com.dev.cinema.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordValidator implements
        ConstraintValidator<PasswordConstraint, String> {
 
    @Override
    public void initialize(PasswordConstraint password) {
    }
 
    @Override
    public boolean isValid(String password,
                           ConstraintValidatorContext constraintValidatorContext) {
        return password != null && (password.length() > 6) && (password.length() < 25);
    }
}
