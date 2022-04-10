package org.junit.validator;

import java.util.List;

import org.junit.runners.model.FrameworkField;
import org.junit.runners.model.TestClass;

public class FieldValidator extends AnnotatableValidator<FrameworkField> {
	@Override
	Iterable<FrameworkField> getAnnotatablesForTestClass(TestClass testClass) {
		return testClass.getAnnotatedFields();
	}

	@Override
	List<Exception> validateAnnotatable(AnnotationValidator validator, FrameworkField field) {
		return validator.validateAnnotatedField(field);
	}
}
