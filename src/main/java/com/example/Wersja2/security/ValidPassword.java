package com.example.Wersja2.security;



import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented

@Target({ ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PasswordConstraintValidator.class)
public @interface ValidPassword {
    String message() default "Hasło musi zawierać przynajmniej jedną wielką literę i cyfrę";

    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

