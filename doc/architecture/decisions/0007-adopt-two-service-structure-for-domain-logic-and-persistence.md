# ADR-006: Adopt Two-Service Structure for Domain Logic and Persistence

## Status

Accepted

## Context

Within the domain layer of the application, both basic persistence operations (e.g., create, read, update, delete) and
higher-level business behaviors (e.g., applying rules, validations, or transformations) must be supported.

To improve modularity and clarity while avoiding code duplication or tightly coupled services, a consistent structure
for organizing domain services is required.

## Decision

We adopted a **two-service structure** for each domain feature that requires both business logic and data access:

- A **public service** (e.g., `XxxService`), responsible for exposing domain use cases to external layers such as
  controllers. It orchestrates business behavior and acts as the single entry point for the feature.
- A **package-private CRUD service** (e.g., `XxxCrudService`), responsible for encapsulating simple persistence
  operations and persistence-related rules, such as setting timestamps or throwing not-found exceptions.

The public service delegates to the CRUD service where needed. Controllers and other external components should only
depend on the public service.

### Rationale:

- Promotes **separation of concerns** between business behavior and persistence.
- Keeps **internal persistence logic reusable** but not publicly exposed.
- Simplifies **testing and maintenance** by isolating logic responsibilities.
- Makes domain service APIs **intentional and focused**, avoiding unnecessary exposure of technical details to the
  controller layer.

## Consequences

- Persistence logic can be reused across domain services but remains internal to the package.
- Controllers and external layers interact only with the public service.
- Slight increase in indirection, but significant gain in modularity and scalability.

## Alternatives Considered

- **Single service per domain feature**: Simpler but risks mixing responsibilities and duplicating low-level persistence
  operations.
- **Facade over multiple specialized services**: More complex and better suited for large-scale or highly modularized
  applications.

## Summary

This approach ensures that each domain feature maintains a clear boundary between persistence and business logic while
exposing a clean, public service interface. It supports long-term maintainability, encapsulation, and domain scalability
without introducing unnecessary complexity.
