package com.felipedsr.springlibsvalidation;

import org.apache.commons.beanutils.PropertyUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.reflect.InvocationTargetException;
import java.time.LocalDate;

public class AfterValidator implements ConstraintValidator<After, Object> {

    private String dateField;
    private String shouldBeAfterDateField;
    private boolean isEqualValid;

    @Override
    public void initialize(After constraintAnnotation) {
        dateField = constraintAnnotation.date();
        shouldBeAfterDateField = constraintAnnotation.shouldBeAfterDate();
        isEqualValid = constraintAnnotation.isEqualsValid();
    }

    @Override
    public boolean isValid(Object entity, ConstraintValidatorContext context) {
        if (entity == null) {
            return true;
        }
        try {
            LocalDate shoudBeAfterDate = (LocalDate) PropertyUtils.getNestedProperty(entity, shouldBeAfterDateField);
            LocalDate date = (LocalDate) PropertyUtils.getNestedProperty(entity, dateField);
            return date == null || shoudBeAfterDate == null ||
                    (isEqualValid && date.isEqual(shoudBeAfterDate)) ||
                    date.isAfter(shoudBeAfterDate);

        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new IllegalArgumentException(e.getMessage(), e);
        }
    }

}