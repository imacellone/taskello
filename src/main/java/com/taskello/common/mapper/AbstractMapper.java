package com.taskello.common.mapper;

import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.Optional;

public abstract class AbstractMapper<I, E> implements BiMapper<I, E> {

    private final Class<I> internalClass;
    private final Class<E> externalClass;
    private final ModelMapper modelMapper;

    protected AbstractMapper(final Class<I> internalClass,
                             final Class<E> externalClass,
                             final ModelMapper modelMapper) {
        this.internalClass = internalClass;
        this.externalClass = externalClass;
        this.modelMapper = modelMapper;
    }

    public List<I> toInternals(final List<E> externals) {
        return Optional.ofNullable(externals)
                .orElseGet(List::of)
                .stream()
                .map(this::toInternal)
                .toList();
    }

    public I toInternal(final E external) {
        return modelMapper.map(external, internalClass);
    }

    public List<E> toExternals(final List<I> internals) {
        return Optional.ofNullable(internals)
                .orElseGet(List::of)
                .stream()
                .map(this::toExternal)
                .toList();
    }

    public E toExternal(final I internal) {
        return modelMapper.map(internal, externalClass);
    }
}
