# Clove-MSA-BE

## Database Isolation

Each microservice now connects to its own MySQL instance instead of
sharing a single database server. The datasource URLs have been updated
so that services resolve to hosts such as `auth-db`, `event-db`,
`merch-db`, `ticket-db`, and `seat-db`. This separation improves
fault isolation and aligns with recommended MSA database per service
patterns.