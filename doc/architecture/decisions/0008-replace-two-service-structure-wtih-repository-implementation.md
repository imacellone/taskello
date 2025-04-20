# ADR-0008: replace-two-service-structure-wtih-repository-implementation

Date: 2025-04-20

## Status

- Accepted

- Supersedes [ADR-0007: Adopt Two-Service Structure for Domain Logic and Persistence
](0007-adopt-two-service-structure-for-domain-logic-and-persistence.md)

## Context

Previously, the domain logic for a feature was organized into two services:

- A **public service**, responsible for orchestrating business behavior and exposed to the controller layer.
- A **package-private CRUD service**, responsible for simple persistence operations.

This structure aimed to promote separation of concerns and reduce code duplication,
but introduced additional layers and indirection even for relatively straightforward cases.

## Decision

We removed the `CrudService` layer and replaced it with a domain-facing **repository interface** (`TaskRepository`),
implemented by a **persistence-specific class** (`TaskRepositoryImpl`) in the repository layer.

The public domain service now delegates directly to this repository interface, which encapsulates:

- Data access and persistence operations
- Domain-to-entity mapping
- Persistence rules such as setting timestamps or checking existence

This repository implementation lives in the repository layer and is registered using Spring’s dependency injection.

## Rationale

- Simplifies service structure by removing the unnecessary CRUD layer
- Maintains strict **layer isolation** between domain and persistence
- Ensures the domain service only sees and works with domain models
- Keeps persistence-specific concerns fully encapsulated
- Reduces the number of classes while improving clarity and maintainability

## Consequences

- Persistence logic is now centralized in the repository implementation
- Domain services are slimmer and focused on orchestration
- Infrastructure layer is responsible for mapping and data rules
- The original ADR-0007 structure is no longer in use and has been superseded

## Summary

We replaced the two-service structure with a repository implementation approach, 
resulting in a more maintainable and better-isolated architecture.
 