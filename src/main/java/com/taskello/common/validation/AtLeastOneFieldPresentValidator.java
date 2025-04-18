package com.taskello.common.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

public class AtLeastOneFieldPresentValidator
        implements ConstraintValidator<AtLeastOneFieldPresent, Object> {

    @Override
    public boolean isValid(final Object object,
                           final ConstraintValidatorContext context) {
        return Objects.nonNull(object) &&
                getAllGetters(object)
                        .map(method -> invoke(method, object))
                        .map(this::convertToNullIfEmptyString)
                        .anyMatch(Objects::nonNull);
    }

    private Stream<Method> getAllGetters(final Object object) {
        return getMethods(object).filter(this::isGetter);
    }

    private Stream<Method> getMethods(final Object object) {
        if (object == null) {
            return Stream.empty();
        }
        return Arrays.stream(object.getClass().getMethods());
    }

    private boolean isGetter(final Method method) {
        return Optional.ofNullable(method)
                .filter(m -> m.getParameterCount() == 0)
                .filter(m -> {
                    final String methodName = m.getName();
                    final Class<?> returnType = m.getReturnType();
                    return !"getClass".equals(methodName) &&
                            ((methodName.startsWith("get") && !void.class.equals(returnType)) ||
                                    (methodName.startsWith("is") && boolean.class.equals(returnType)));
                })
                .isPresent();
    }

    private Object invoke(final Method method, final Object object) {
        try {
            return method.invoke(object);
        } catch (IllegalAccessException | InvocationTargetException e) {
            // todo: revisit this
            throw new RuntimeException(e);
        }
    }

    private Object convertToNullIfEmptyString(Object object) {
        return object instanceof String s && s.isEmpty() ? null : object;
    }
}
