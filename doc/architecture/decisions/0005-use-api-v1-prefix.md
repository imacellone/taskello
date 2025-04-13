## ADR Title: Start API with `/v1` Path Prefix for Versioning

Date: 2025-04-13

## Status

Accepted

## Context

Even though our API currently has only one version, we anticipate future changes that may introduce breaking updates.
Versioning the API from the start provides a foundation for clean evolution without disrupting existing consumers.

## Decision

All API endpoints will include a `/v1` prefix from the beginning. Example: `.../api/v1/tasks/...`.

This versioning will be applied at the controller level using `@RequestMapping("/v1/...")`.

## Rationale

- **Future-proofing**: Adding `/v1` upfront avoids a large-scale refactor later.
- **Non-breaking upgrades**: Allows future versions (`/v2`, etc.) to coexist with older ones.
- **Familiar pattern**: Aligns with widely adopted RESTful API design practices.
- **Simplifies routing**: Easier to configure reverse proxies and API gateways.

## Consequences

- Slightly longer URLs (`/api/v1/...`) from the start.
- Enables smooth version management as the API evolves.

## Alternatives Considered

- Starting without a version prefix (e.g., `/api/...`) — rejected to avoid refactoring and compatibility issues later
  when versioning becomes necessary.
