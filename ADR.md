# Architecture Decision Record (ADR)

## Status
The inventory service provides a RESTful API for managing product inventory.

## Context

Customer are facing issues with inventory management, including: inconsistency and latency.

There are several goals for the new system:

1. Optimize inventory consistency
2. Reduce stock update latency
3. Lower operation cost
4. Ensure Security And Observability

## Decision

1. Replace retail's database to hook up with a centralized inventory system. It will ensure consistency and reduce latency by having a single source of truth.
2. Introduce a caching layer to reduce read latency and improve performance. Implement cache invalidation strategies to maintain data consistency.
3. Implement a RESTful API for inventory management to take over key operations to reduce operational costs. Some key operations include:
   - Get All Items
   - Get Item by ID
   - Increment Stock
   - Decrement Stock
   - Add New Item
   - Delete Item
   - Update Item Details
   
   For the proof of concept, it will be implemented using the first four operations.
4. Implement security measures such as authentication and authorization to protect inventory data. Include Prometheus for monitoring and logging to enhance observability.

## Consequences

1. Make sure the centralized inventory database is highly available and has consistency mechanisms in place such as Sql Server, PostgreSQL, MySQL.
2. The backend service should be stateless to facilitate horizontal scaling and improve fault tolerance.
3. The caching layer should be designed to handle cache misses and ensure data consistency with the primary database.
4. The RESTful API should be designed following best practices to ensure scalability and maintainability.
5. Security measures should be regularly updated to address emerging threats and vulnerabilities.
6. Monitoring and logging should be comprehensive to provide insights into system performance and facilitate troubleshooting.