package org.junit.validator;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;

import org.junit.runners.model.Annotatable;
import org.junit.runners.model.TestClass;

public abstract class AnnotatableValidator<T extends Annotatable> {
	private static final AnnotationValidatorFactory ANNOTATION_VALIDATOR_FACTORY = new AnnotationValidatorFactory();

	abstract Iterable<T> getAnnotatablesForTestClass(TestClass testClass);

	abstract List<Exception> validateAnnotatable(AnnotationValidator validator, T annotatable);

	public List<Exception> validateTestClass(TestClass testClass) {
		List<Exception> validationErrors = new ArrayList<>();
		for (T annotatable : getAnnotatablesForTestClass(testClass)) {
			List<Exception> additionalErrors = validateAnnotatable(annotatable);
			validationErrors.addAll(additionalErrors);
		}
		return validationErrors;
	}

	private List<Exception> validateAnnotatable(T annotatable) {
		List<Exception> validationErrors = new ArrayList<>();
		for (Annotation annotation : annotatable.getAnnotations()) {
			Class<? extends Annotation> annotationType = annotation.annotationType();
			ValidateWith validateWith = annotationType.getAnnotation(ValidateWith.class);
			if (validateWith != null) {
				AnnotationValidator annotationValidator = ANNOTATION_VALIDATOR_FACTORY
						.createAnnotationValidator(validateWith);
				List<Exception> errors = validateAnnotatable(annotationValidator, annotatable);
				validationErrors.addAll(errors);
			}
		}
		return validationErrors;
	}
}



