
package com.bernardomg.validation.test.util;

import java.util.List;
import java.util.Optional;

import com.bernardomg.validation.domain.model.FieldFailure;
import com.bernardomg.validation.validator.AbstractFieldRuleValidator;

public final class TestFieldRuleValidator extends AbstractFieldRuleValidator<String> {

    public TestFieldRuleValidator() {
        super(List.of((obj -> Optional.of(FieldFailure.of("field", "code", obj)))));
    }

}
