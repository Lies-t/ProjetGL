package org.junit.validator;

import java.util.List;

import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.TestClass;

public class MethodValidator extends AnnotatableValidator<FrameworkMethod> {
	@Override
	Iterable<FrameworkMethod> getAnnotatablesForTestClass(TestClass testClass) {
		return testClass.getAnnotatedMethods();
	}

	@Override
	List<Exception> validateAnnotatable(AnnotationValidator validator, FrameworkMethod method) {
		return validator.validateAnnotatedMethod(method);
	}
}
