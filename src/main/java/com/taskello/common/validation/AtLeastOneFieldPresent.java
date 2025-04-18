package com.taskello.common.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

/**
 * Validates that at least one getter method on the annotated object returns a non-null (and non-empty, for strings) value.
 * <p>
 * This constraint applies at the class level. It uses reflection to invoke all public no-arg getter methods (including
 * those starting with <code>get</code> and <code>is</code> for primitive booleans).
 * </p>
 * <p>
 * A field is considered "present" if:
 * <ul>
 *   <li>Its getter returns a non-null value</li>
 *   <li>If it is a {@link String}, it must not be empty</li>
 * </ul>
 * </p>
 * If all getters return null or empty strings, the constraint will fail.
 */
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = AtLeastOneFieldPresentValidator.class)
public @interface AtLeastOneFieldPresent {

    String message() default "At least one field must be non-null or a non-empty string";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
