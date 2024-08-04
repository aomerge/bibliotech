package com.aomerge.rentbooks.config.validation.fit;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class AuthorizationHeaderValidator implements ConstraintValidator<ValidAuthorizationHeader, String> {
    private String message;

    @Override
    public void initialize(ValidAuthorizationHeader constraintAnnotation) {
        message = constraintAnnotation.message();
    }

    @Override
    public boolean isValid(String authorizationHeader, ConstraintValidatorContext context) {
        if (authorizationHeader == null || authorizationHeader.trim().isEmpty()) {
            System.out.println(message);
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(this.message)
                    .addConstraintViolation();
            return false;
        }
        return authorizationHeader.startsWith("Bearer ");
    }
}
