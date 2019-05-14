package com.felipedsr.springlibsvalidation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Repeatable(value = After.List.class)
@Constraint(validatedBy = {AfterValidator.class})
public @interface After {

    String message() default "{com.felipedsr.validator.After.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    String date();

    String shouldBeAfterDate();

    boolean isEqualsValid() default true;

    @Documented
    @Target({TYPE})
    @Retention(RUNTIME)
    @interface List {
        After[] value();
    }
}
