# Spring Boot - Base Backend Project

built by CongPV

## Architecture

I divided the project into 4 layers:

- **Application**: Rest APIs, passing user input to services
- **Core**: domain logic, divided into services
- **Repository**: layer for interaction with models and performing DB operations
- **Infrastructure**: support 3 higher layers

## Features

- [X] Basic REST API
- [X] Multiple DataSource Configuration
- [X] Read/Write Splitting
- [X] Caching Local
- [X] Multiple Cache Manager
- [X] Token based Authentication
- [ ] Session based Authentication
- [X] Swagger
- [X] MultiThread
- [ ] Background Job
- [ ] Authorization
- [ ] Retry
- [X] MDC logging
- [ ] Monitoring with Prometheus
- [ ] Sentry Integration
- [X] Pagination
- [X] Exception handling

## Setup:

- Requirements: MySQL, Redis
- Update your database config in **application.properties**
- Install [Redis Commander](https://github.com/joeferner/redis-commander) which will support you to
  check your Redis cache
- Run 'redis-commander --redis-db 10 --redis-password 123' (depends on your db/password config)

## Notes:

- Check your swagger: http://localhost:8088/swagger-ui.html
- Redis Commander UI: http://127.0.0.1:8081