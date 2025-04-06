# 2. Set Hibernate JDBC timezone to UTC

Date: 2025-04-06

## Status

Accepted

## Context

We are using H2, but its connection string does not support time zone configuration—unlike MySQL, for example.
Still, we want to handle time zones correctly from the start.

## Decision

We chose to apply the setting below to avoid the complexity of handling time zones manually.

```
spring.jpa.properties.hibernate.jdbc.time_zone=UTC
```

## Consequences

Although this approach resolves our issue, we must keep in mind that it is Hibernate-specific and not part of the JPA
specification.