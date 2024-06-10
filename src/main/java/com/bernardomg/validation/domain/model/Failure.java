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
 * Immutable failure object.
 *
 * @author Bernardo Mart&iacute;nez Garrido
 *
 */
@Value
@Builder
public final class Failure {

    /**
     * Builds a failure with the received code.
     *
     * @param code
     *            failure code
     * @return failure with the code
     */
    public static Failure of(final String code) {
        return Failure.builder()
            .message(code)
            .code(code)
            .build();
    }

    /**
     * Builds a failure with the received message and code.
     *
     * @param message
     *            failure message
     * @param code
     *            failure code
     * @return failure with the code
     */
    public static Failure of(final String message, final String code) {
        return Failure.builder()
            .message(message)
            .code(code)
            .build();
    }

    /**
     * Code identifying the failure.
     */
    private final String code;

    /**
     * The failure message.
     */
    private final String message;

}
