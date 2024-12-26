/**
 * The MIT License (MIT)
 * <p>
 * Copyright (c) 2023-2025 the original author or authors.
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

import java.io.Serializable;
import java.util.Objects;

/**
 * Field error message. Usually represents an error when validation a single field from an object. The validation
 * process may generate several of these.
 *
 * @author Bernardo Mart&iacute;nez Garrido
 *
 */
public record FieldFailure(String code, String message, String field, Object value) implements Serializable {

    public FieldFailure {
        Objects.requireNonNull(code, "Received null code");
        Objects.requireNonNull(message, "Received null message");
        Objects.requireNonNull(field, "Received null field");
    }

    public FieldFailure(final String code, final String field, final Object value) {
        this(code, String.format("%s.%s", field, code), field, value);
    }

    /**
     * Serialisation id.
     */
    private static final long serialVersionUID = 8492078591901480534L;

}
