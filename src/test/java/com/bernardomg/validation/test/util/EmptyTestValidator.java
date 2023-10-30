
package com.bernardomg.validation.test.util;

import java.util.Collection;

import com.bernardomg.validation.AbstractValidator;
import com.bernardomg.validation.failure.FieldFailure;

public final class EmptyTestValidator extends AbstractValidator<String> {

    @Override
    protected final void checkRules(final String obj, final Collection<FieldFailure> failures) {}

}
