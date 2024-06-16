
package com.bernardomg.validation.test.unit;

import org.assertj.core.api.Assertions;
import org.assertj.core.api.InstanceOfAssertFactories;
import org.assertj.core.api.ThrowableAssert.ThrowingCallable;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.bernardomg.validation.domain.exception.FieldFailureException;
import com.bernardomg.validation.test.config.validator.EmptyFieldRuleValidator;
import com.bernardomg.validation.test.config.validator.TestFieldRuleValidator;

@DisplayName("AbstractFieldRuleValidator")
public class AbstractFieldRuleValidatorTest {

    @Test
    @DisplayName("When there are validation failures it throws the expected exception")
    void testValidate_failures() {
        final ThrowingCallable execution;

        // WHEN
        execution = () -> new TestFieldRuleValidator().validate("abc");

        // THEN
        Assertions.assertThatThrownBy(execution)
            .as("validation exception")
            .isInstanceOf(FieldFailureException.class)
            .asInstanceOf(InstanceOfAssertFactories.type(FieldFailureException.class))
            .extracting(FieldFailureException::getFailures)
            .as("failures")
            .asInstanceOf(InstanceOfAssertFactories.LIST)
            .hasSize(1);
    }

    @Test
    @DisplayName("When there are no validation failures it throws no exception")
    void testValidate_noFailures() {
        final ThrowingCallable execution;

        // WHEN
        execution = () -> new EmptyFieldRuleValidator().validate("abc");

        // THEN
        Assertions.assertThatCode(execution)
            .as("validation execution")
            .doesNotThrowAnyException();
    }

}
