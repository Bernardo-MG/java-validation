
package com.bernardomg.validation.test.unit;

import org.assertj.core.api.Assertions;
import org.assertj.core.api.ThrowableAssert.ThrowingCallable;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.bernardomg.validation.AbstractValidator;
import com.bernardomg.validation.failure.exception.FieldFailureException;
import com.bernardomg.validation.test.util.TestValidator;

@DisplayName("AbstractValidator")
public class AbstractValidatorTest {

    final AbstractValidator<String> testValidator = new TestValidator();

    @Test
    @DisplayName("Throws the expected exception")
    void testIsValid_AllLowerCase() {
        final ThrowingCallable      execution;
        final FieldFailureException exception;

        execution = () -> testValidator.validate("abc");

        Assertions.assertThatThrownBy(execution)
            .isInstanceOf(FieldFailureException.class);

        exception = Assertions.catchThrowableOfType(execution, FieldFailureException.class);

        Assertions.assertThat(exception.getFailures())
            .hasSize(1);
    }

}
