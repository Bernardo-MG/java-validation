
package com.bernardomg.validation.test.unit;

import org.assertj.core.api.Assertions;
import org.assertj.core.api.ThrowableAssert.ThrowingCallable;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.bernardomg.validation.failure.exception.FieldFailureException;
import com.bernardomg.validation.test.util.EmptyTestValidator;
import com.bernardomg.validation.test.util.TestValidator;

@DisplayName("AbstractValidator")
public class AbstractValidatorTest {

    @Test
    @DisplayName("When there are validation failures it throws the expected exception")
    void testValidate_failures() {
        final ThrowingCallable      execution;
        final FieldFailureException exception;

        execution = () -> new TestValidator().validate("abc");

        Assertions.assertThatThrownBy(execution)
            .isInstanceOf(FieldFailureException.class);

        exception = Assertions.catchThrowableOfType(execution, FieldFailureException.class);

        Assertions.assertThat(exception.getFailures())
            .hasSize(1);
    }

    @Test
    @DisplayName("When there are no validation failures it throws no exception")
    void testValidate_noFailures() {
        final ThrowingCallable execution;

        execution = () -> new EmptyTestValidator().validate("abc");

        Assertions.assertThatCode(execution)
            .doesNotThrowAnyException();
    }

}
