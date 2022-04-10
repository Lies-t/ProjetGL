package org.junit.validator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.runners.model.TestClass;

/**
 * An {@code AnnotationsValidator} validates all annotations of a test class,
 * including its annotated fields and methods.
 * 
 * @since 4.12
 */
public final class AnnotationsValidator implements TestClassValidator {
    private static final List<AnnotatableValidator<?>> VALIDATORS = Arrays.<AnnotatableValidator<?>>asList(
            new ClassValidator(), new MethodValidator(), new FieldValidator());

    /**
     * Validate all annotations of the specified test class that are be
     * annotated with {@link ValidateWith}.
     * 
     * @param testClass
     *            the {@link TestClass} that is validated.
     * @return the errors found by the validator.
     */
    public List<Exception> validateTestClass(TestClass testClass) {
        List<Exception> validationErrors= new ArrayList<>();
        for (AnnotatableValidator<?> validator : VALIDATORS) {
            List<Exception> additionalErrors= validator
                    .validateTestClass(testClass);
            validationErrors.addAll(additionalErrors);
        }
        return validationErrors;
    }

}
