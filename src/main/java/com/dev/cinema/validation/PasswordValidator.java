package com.dev.cinema.validation;

import com.dev.cinema.model.dto.UserRequestDto;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordValidator implements ConstraintValidator<PasswordConstraint, Object> {
 
    @Override
    public void initialize(PasswordConstraint password) {
    }
 
    @Override
    public boolean isValid(Object object, ConstraintValidatorContext constraintValidatorContext) {
        UserRequestDto userResponseDto = (UserRequestDto) object;
        return userResponseDto.getPassword().equals(userResponseDto.getRepeatPassword());
    }
}
