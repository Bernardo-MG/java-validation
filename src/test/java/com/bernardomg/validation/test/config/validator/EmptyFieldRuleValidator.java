
package com.bernardomg.validation.test.config.validator;

import java.util.List;

import com.bernardomg.validation.validator.AbstractFieldRuleValidator;

public final class EmptyFieldRuleValidator extends AbstractFieldRuleValidator<String> {

    public EmptyFieldRuleValidator() {
        super(List.of());
    }

}
