package com.aomerge.rentbooks.config.validation.fit;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = AuthorizationHeaderValidator.class)
public @interface ValidAuthorizationHeader {
    String message() default "Invalid Authorization Header";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
