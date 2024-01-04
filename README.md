# Simple-banking system

> The idea was to abstract as much as possible from adapters, 
> infrastructure and any dependencies that could affect business logic and write
> fully-functional core of application - business logic, that could be used afterward by adapters
> to execute business logic based on interactions from outside world.

> In this project, all the logic was build using DDD, aggregates, separation of contexts, domain event publishing.
> Business logic is fully encapsulated in domain layer. Any business object (entity) can be changed only by an aggregate
> that operates with it, so the whole logic is safe from changes from outside of aggregate. Aggregate forces all data to be
> valid using explicit invariants, so objects can't exist in invalid state.
> 
> TODO:
> - Add tests
> - Add credits logic