
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
- [ ] Caching Local
- [ ] Swagger
- [ ] MultiThread
- [ ] Background Job
- [ ] Token based Authentication
- [ ] Authorization
- [ ] Retry
- [ ] MDC logging
- [ ] Monitoring with Prometheus
- [ ] Sentry