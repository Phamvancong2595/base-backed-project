# Spring Boot Based Backend Project

built by CongPV

## Architecture

I divided the project into 4 layers:

    ├── application               # Controllers, passing user input into services respectively
    ├── domain                    # Domain logic, divided into services
    ├── infrastructure            # Config, repository, third parties,...
    └── shared                    # constants, DTO, utils,...

## Features

- [X] Basic REST API
- [X] Multiple DataSource Configuration
- [X] Read/Write Splitting
- [X] Caching Local with Caffeine
- [X] Multiple Cache Manager
- [X] Fix Token Authentication
- [X] JWT Authentication
- [ ] Session based Authentication
- [X] Swagger
- [X] MultiThread
- [X] Background Job
- [ ] Authorization
- [X] Retry
- [X] MDC logging
- [ ] Monitoring with Prometheus
- [X] Sentry Integration
- [X] Pagination
- [X] Exception handling
- [X] Event Handling
- [ ] Distributed Transactions
- [ ] Race Condition Handling
- [ ] Kafka Producer/Consumer (Json, Avro)
## Setup:

- Requirements: MySQL, Redis
- Update your database config in **application.properties**
- Install [Redis Commander](https://github.com/joeferner/redis-commander) which will support you to
  check your Redis cache
- Run 'redis-commander --redis-db 10 --redis-password 123' (depends on your db/password config)

## Notes:

- Check your swagger: http://localhost:8088/swagger-ui.html
- Redis Commander UI: http://127.0.0.1:8081