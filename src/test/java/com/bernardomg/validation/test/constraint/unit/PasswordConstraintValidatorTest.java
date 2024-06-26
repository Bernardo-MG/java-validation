
package com.bernardomg.validation.test.constraint.unit;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.bernardomg.validation.constraint.PasswordConstraintValidator;

import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.ConstraintValidatorContext.ConstraintViolationBuilder;

@ExtendWith(MockitoExtension.class)
@DisplayName("PasswordConstraintValidator")
public class PasswordConstraintValidatorTest {

    @Mock
    private ConstraintValidatorContext        context;

    private final PasswordConstraintValidator validator = new PasswordConstraintValidator();

    private final void initializeErrorResponse() {
        Mockito.when(context.buildConstraintViolationWithTemplate(ArgumentMatchers.anyString()))
            .thenReturn(Mockito.mock(ConstraintViolationBuilder.class));
    }

    @Test
    @DisplayName("A password with all lower case is invalid")
    void testIsValid_AllLowerCase() {
        final boolean valid;

        // GIVEN
        initializeErrorResponse();

        // WHEN
        valid = validator.isValid("abcdefgh", context);

        // THEN
        Assertions.assertThat(valid)
            .isFalse();
    }

    @Test
    @DisplayName("A complex password is valid")
    void testIsValid_Complex() {
        final boolean valid;

        // WHEN
        valid = validator.isValid("Abcdef1!", context);

        // THEN
        Assertions.assertThat(valid)
            .isTrue();
    }

    @Test
    @DisplayName("An empty password is invalid")
    void testIsValid_Empty() {
        final boolean valid;

        // GIVEN
        initializeErrorResponse();

        // WHEN
        valid = validator.isValid("", context);

        // THEN
        Assertions.assertThat(valid)
            .isFalse();
    }

    @Test
    @DisplayName("A password with an empty space is invalid")
    void testIsValid_EmptySpace() {
        final boolean valid;

        // GIVEN
        initializeErrorResponse();

        // WHEN
        valid = validator.isValid("Abcde f1!", context);

        // THEN
        Assertions.assertThat(valid)
            .isFalse();
    }

}
