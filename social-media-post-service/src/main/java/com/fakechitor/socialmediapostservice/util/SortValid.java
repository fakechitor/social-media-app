package com.fakechitor.socialmediapostservice.util;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = SortingValidation.class)
public @interface SortValid {
    String message() default "Enter 'asc' or 'desc'. Or left that field empty for default sorting.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
