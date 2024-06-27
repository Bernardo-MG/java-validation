/**
 * The MIT License (MIT)
 * <p>
 * Copyright (c) 2023-2024 the original author or authors.
 * <p>
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * <p>
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * <p>
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.bernardomg.validation.test.assertion;

import org.assertj.core.api.Assertions;
import org.assertj.core.api.InstanceOfAssertFactories;
import org.assertj.core.api.SoftAssertions;
import org.assertj.core.api.ThrowableAssert.ThrowingCallable;

import com.bernardomg.validation.domain.exception.FieldFailureException;
import com.bernardomg.validation.domain.model.FieldFailure;

/**
 * Assertions for testing validations.
 */
public final class ValidationAssertions {

    /**
     * Asserts that the callable throws the received exception.
     *
     * @param callable
     *            callable which throws the exception
     * @param expected
     *            expected exception
     */
    public static final void assertThatFieldFails(final ThrowingCallable callable, final FieldFailure expected) {
        final FieldFailureException exception;

        // Catch exception
        exception = Assertions.catchThrowableOfType(callable, FieldFailureException.class);

        SoftAssertions.assertSoftly(softly -> {
            final FieldFailure failure;

            // There is a single failure
            softly.assertThat(exception)
                .as("failures")
                .isNotNull()
                .extracting(FieldFailureException::getFailures)
                .asInstanceOf(InstanceOfAssertFactories.LIST)
                .hasSize(1);

            failure = exception.getFailures()
                .iterator()
                .next();

            // Field
            softly.assertThat(failure.getField())
                .withFailMessage("Expected failure field '%s' but got '%s'", expected.getField(), failure.getField())
                .isEqualTo(expected.getField());
            // Code
            softly.assertThat(failure.getCode())
                .withFailMessage("Expected failure code '%s' but got '%s'", expected.getCode(), failure.getCode())
                .isEqualTo(expected.getCode());
            // Message
            softly.assertThat(failure.getMessage())
                .withFailMessage("Expected failure message '%s' but got '%s'", expected.getMessage(),
                    failure.getMessage())
                .isEqualTo(expected.getMessage());
            // Value
            softly.assertThat(failure.getValue())
                .withFailMessage("Expected failure value '%s' but got '%s'", expected.getValue(), failure.getValue())
                .isEqualTo(expected.getValue());
        });
    }

    private ValidationAssertions() {
        super();
    }

}
