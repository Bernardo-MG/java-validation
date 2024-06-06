
package com.bernardomg.validation.test.util;

import java.util.Collection;

import com.bernardomg.validation.domain.model.FieldFailure;
import com.bernardomg.validation.validator.AbstractValidator;

public final class TestValidator extends AbstractValidator<String> {

    @Override
    protected final void checkRules(final String obj, final Collection<FieldFailure> failures) {
        FieldFailure failure;

        failure = FieldFailure.of("field", "code", obj);
        failures.add(failure);
    }

}
