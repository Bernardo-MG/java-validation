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

package com.bernardomg.validation.validator;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bernardomg.validation.domain.exception.FieldFailureException;
import com.bernardomg.validation.domain.model.FieldFailure;

/**
 * Validator which checks the fields of the received object. It will apply a collection of rules, throwing a
 * {@link FieldFailureException} if any {@link FieldFailure} is generated.
 *
 * @author Bernardo Mart&iacute;nez Garrido
 *
 * @param <T>
 *            type being validated
 */
public final class FieldRuleValidator<T> implements Validator<T> {

    /**
     * Logger for the class.
     */
    private static final Logger          log           = LoggerFactory.getLogger(FieldRuleValidator.class);
    /**
     * The set of rules to apply.
     */
    private final Collection<FieldRule<T>> rules;

    @SafeVarargs
    public FieldRuleValidator(final FieldRule<T>... rules) {
        super();

        this.rules = List.of(rules);
    }

    @Override
    public final void validate(final T obj) {
        final Collection<FieldFailure> failures;

        log.debug("Validating {} with rules {}", obj, rules);

        // Apply each rule on the object and collect failures
        failures = rules.stream()
            .map(r -> {
                final Optional<FieldFailure> failure;

                log.debug("Applying rule {} on {}", r, obj);
                failure = r.check(obj);
                log.debug("Applied rule {} on {}, which returned failure {}", r, obj, failure);

                return failure;
            })
            .filter(Optional::isPresent)
            .map(Optional::get)
            .toList();

        if (!failures.isEmpty()) {
            log.debug("Validated {} with failures: {}", obj, failures);
            // TODO: check this, it should include the source always
            if (obj instanceof final Serializable serializable) {
                throw new FieldFailureException(serializable, failures);
            }
            throw new FieldFailureException(failures);
        }
    }

}
