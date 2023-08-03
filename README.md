
# Spring Boot - Base Backend Project

built by CongPV

## Architecture
I divided the project into 4 layers:

- **Application**: Rest APIs, passing user input to services
- **Core**: domain logic, divided into services
- **Repository**: layer for interaction with models and performing DB operations
- **Infrastructure**: support 3 higher layers

## Features

- Basic REST API
- Multiple DataSource Configuration
- Read/Write Splitting