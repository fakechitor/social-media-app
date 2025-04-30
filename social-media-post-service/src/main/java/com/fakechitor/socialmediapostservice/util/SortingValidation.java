package com.fakechitor.socialmediapostservice.util;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.List;

public class SortingValidation implements ConstraintValidator<SortValid, String>  {

    private static final List<String> ALLOWED_SORTING = List.of("asc", "desc");

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value != null && ALLOWED_SORTING.contains(value);
    }
}
