# ADR-0006: Use ModelMapper for DTO <-> Entity Mapping

## Status

- Accepted

## Context

The application requires mappings between domain entities and Data Transfer Objects for input and output across the API
layer. To avoid repetitive and error-prone manual mapping logic, especially as the number of DTOs increases, an
automated solution is preferred.

Two options were considered: ModelMapper and MapStruct.

## Decision

We decided to use ModelMapper as the mapping library for this project.

### Reasons:

- Minimal boilerplate: ModelMapper allows mappings to be performed without the need to write explicit mapping interfaces
  or methods, enabling faster development.
- Quick integration: It is simple to set up and requires little configuration to begin using.
- Flexible and dynamic: ModelMapper works well for small to medium-sized projects where mappings are mostly
  straightforward.
- Suitable for prototyping and evolving domains: It adapts well as DTOs and entities change over time, without requiring
  regeneration of code.

## Consequences

- ModelMapper relies on runtime reflection, which can make mapping logic harder to debug in complex or ambiguous cases.
- It offers slightly lower performance than code-generated alternatives such as MapStruct, though this is acceptable for
  the current scale of the project.
- Developers need to be cautious with nested mappings or property mismatches and may need to provide additional
  configuration in such cases.

## Alternatives Considered

- MapStruct: Offers compile-time safety, better runtime performance, and improved debuggability through generated code.
  However, it requires defining explicit mapping interfaces, which introduces more boilerplate and slows down
  early-stage development.

## Summary

ModelMapper provides a good balance between simplicity and flexibility for this project. Its ease of use and minimal
setup make it a practical choice during the early phases of development, while the performance trade-offs are acceptable
given the current requirements.
