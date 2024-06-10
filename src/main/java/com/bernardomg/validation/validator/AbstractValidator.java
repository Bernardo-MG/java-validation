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

package com.bernardomg.validation.validator;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import com.bernardomg.validation.domain.exception.FieldFailureException;
import com.bernardomg.validation.domain.model.FieldFailure;

import lombok.extern.slf4j.Slf4j;

/**
 * Base implementation for validators which throws a {@link FieldFailureException}. All that it requires is implementing
 * the {@link #checkRules(Object, Collection) checkRules} method, which will populate a list with all the failures.
 *
 * @author Bernardo Mart&iacute;nez Garrido
 *
 * @param <T>
 *            type being validated
 */
@Slf4j
public abstract class AbstractValidator<T> implements Validator<T> {

    @Override
    public final void validate(final T obj) {
        final Collection<FieldFailure> failures;

        failures = new ArrayList<>();

        checkRules(obj, failures);

        if (!failures.isEmpty()) {
            log.debug("Got failures: {}", failures);
            if (obj instanceof final Serializable serializable) {
                throw new FieldFailureException(serializable, failures);
            }
            throw new FieldFailureException(null, failures);
        }
    }

    /**
     * Populates the failures list with all the failed rules applied to the received object.
     *
     * @param obj
     *            object to validate
     * @param failures
     *            failures list to populate
     */
    protected abstract void checkRules(final T obj, final Collection<FieldFailure> failures);

}
