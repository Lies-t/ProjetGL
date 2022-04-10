package org.junit.validator;

import static java.util.Collections.singletonList;

import java.util.List;

import org.junit.runners.model.TestClass;

public class ClassValidator extends AnnotatableValidator<TestClass> {
	@Override
	Iterable<TestClass> getAnnotatablesForTestClass(TestClass testClass) {
		return singletonList(testClass);
	}

	@Override
	List<Exception> validateAnnotatable(AnnotationValidator validator, TestClass testClass) {
		return validator.validateAnnotatedClass(testClass);
	}
}
