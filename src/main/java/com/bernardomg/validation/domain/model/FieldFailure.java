/**
 * The MIT License (MIT)
 * <p>
 * Copyright (c) 2023 the original author or authors.
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

package com.bernardomg.validation.domain.model;

import lombok.Builder;
import lombok.Value;

/**
 * Field error message. Usually represents an error when validation a single field from an object. The validation
 * process may generate several of these.
 *
 * @author Bernardo Mart&iacute;nez Garrido
 *
 */
@Value
@Builder
public final class FieldFailure {

    /**
     * Creates a {@code FieldFailure} for the received arguments. The message will be generated from the arguments.
     *
     * @param field
     *            name of the validated field
     * @param code
     *            failure code
     * @param value
     *            field value during the validation process
     * @return {@code FieldValidationError} for the received arguments
     */
    public static FieldFailure of(final String field, final String code, final Object value) {
        final String message;

        message = String.format("%s.%s", field, code);

        return FieldFailure.builder()
            .message(message)
            .field(field)
            .code(code)
            .value(value)
            .build();
    }

    /**
     * Creates a {@code FieldFailure} for the received arguments.
     *
     * @param message
     *            error message
     * @param field
     *            name of the validated field
     * @param code
     *            failure code
     * @param value
     *            field value during the validation process
     * @return {@code FieldValidationError} for the received arguments
     */
    public static FieldFailure of(final String message, final String field, final String code, final Object value) {
        return FieldFailure.builder()
            .message(message)
            .field(field)
            .code(code)
            .value(value)
            .build();
    }

    /**
     * Code identifying the failure.
     */
    private final String code;

    /**
     * Name of the field which failed the validation.
     */
    private final String field;

    /**
     * The failure message.
     */
    private final String message;

    /**
     * The value which failed the validation.
     */
    private final Object value;

}
