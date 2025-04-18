package com.taskello.common.mapper;

import java.util.List;

/**
 * A bidirectional mapper between an internal domain model and an external representation.
 * <p>
 * <strong>INTERNAL</strong> refers to the domain model used within the core of the application.<br>
 * <strong>EXTERNAL</strong> refers to any representation external to the domain, such as a DTO,
 * a persistence entity, or an API resource.
 * </p>
 * <p>
 * Implementations must support conversion in both directions: INTERNAL to/from EXTERNAL.
 * </p>
 *
 * @param <I> the internal type (typically a domain model)
 * @param <E> the external type (e.g., DTO, entity, or API representation)
 */
public interface BiMapper<I, E> {

    /**
     * Converts an external representation to its internal domain model.
     *
     * @param external the external object to convert
     * @return the corresponding internal object
     */
    I toInternal(final E external);

    /**
     * Converts a list of external representations to their internal domain models.
     *
     * @param externals the external objects to convert
     * @return a list of corresponding internal objects
     */
    List<I> toInternals(final List<E> externals);

    /**
     * Converts an internal domain model to its external representation.
     *
     * @param internal the internal object to convert
     * @return the corresponding external object
     */
    E toExternal(final I internal);

    /**
     * Converts a list of internal domain models to their external representations.
     *
     * @param internals the internal objects to convert
     * @return a list of corresponding external objects
     */
    List<E> toExternals(final List<I> internals);
}
