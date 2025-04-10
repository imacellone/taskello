## ADR Title: Use Jakarta Bean Validation in Input DTO

## Status
Accepted

## Context
We need to validate incoming data to ensure correctness before it reaches the domain layer. Jakarta Bean Validation provides a standard way to apply constraints using annotations like `@NotNull`, `@Size`, etc.

## Decision
We will apply validation annotations **only on Input DTOs**, not on entities.

## Rationale
- Input DTOs will be the **only entry point** for data.
- This approach avoids **duplicated validation logic** across DTOs and entities.
- It keeps domain entities **clean and focused** on persistence logic.

## Consequences
- Input validation is centralized at the boundary.
- Entities remain free of presentation-layer concerns.
- Validation can be tailored per use case via different DTOs.
- Any future entry point must enforce validation as well, or a different validation strategy will need to be adopted.