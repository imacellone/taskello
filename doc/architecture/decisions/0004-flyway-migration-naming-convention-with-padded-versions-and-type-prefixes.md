## ADR Title: Flyway Migration Naming Convention with Padded Versions and Type Prefixes

Date: 2025-04-10

## Status

Accepted

## Context

We use Flyway to manage versioned SQL migrations for our database schema and data changes. To maintain clarity and consistency, especially as the project scales, we need a clear naming convention that supports correct ordering and quick identification of the migration type.

## Decision

We will name Flyway migration scripts using the following pattern:
- `V` followed by a **4-digit** zero-padded version number to ensure correct lexicographic and numeric ordering.
- Double underscore `__` separates the version number from the description.
- The description starts with a type prefix:
    - `DDL` for schema changes (e.g., tables, columns, indexes).
    - `DML` for data manipulation (e.g., inserts, updates, deletes).
- A short, lowercase, underscore-separated description follows the prefix.

Examples:
- `V0001__DDL_create_task_table.sql`
- `V0002__DML_seed_initial_tasks.sql`

## Rationale
- Zero-padded versioning ensures correct file sorting in tools like IntelliJ and Git.
- Type prefixes make it easy to distinguish between structural and data-related migrations.
- Descriptive filenames help developers understand the purpose of each migration at a glance.

## Consequences
- Migrations will remain well-organized and human-readable over time.
- Future developers can quickly understand and follow the naming convention.
- Consistency reduces errors and confusion when reviewing or debugging migration histories.

## Alternatives Considered
- Using unpadded version numbers (e.g., `V1`, `V2`) — rejected due to poor sorting in most tools.
- Omitting the `DDL`/`DML` prefix — rejected to maintain clarity between schema and data changes.