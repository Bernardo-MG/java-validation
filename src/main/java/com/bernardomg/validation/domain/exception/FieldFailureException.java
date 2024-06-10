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

package com.bernardomg.validation.domain.exception;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;
import java.util.Objects;

import com.bernardomg.validation.domain.model.FieldFailure;

import lombok.Getter;

/**
 * Exception for field failures. Contains multiple field failures, all for the same object.
 *
 * @author Bernardo Mart&iacute;nez Garrido
 *
 */
@Getter
public class FieldFailureException extends RuntimeException {

    /**
     * Generated serial.
     */
    private static final long              serialVersionUID = -2003757248463512577L;

    /**
     * All the failures for the validated object.
     */
    private final Collection<FieldFailure> failures;

    /**
     * The object which caused the field filure.
     */
    private final Serializable             source;

    public FieldFailureException(final Serializable src, final Collection<FieldFailure> fails) {
        super();

        source = src;
        failures = Objects.requireNonNull(fails);
    }

    public FieldFailureException(final Serializable src, final FieldFailure fail) {
        super(fail.getMessage());

        source = src;
        failures = Arrays.asList(fail);
    }

}
