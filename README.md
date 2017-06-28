# Elasticsearch Service

A simple example showcase for using Elasticsearch within a Spring microservice.

More about it: https://thlaegler.github.io/microservice


## Prerequisites

### Installation

You need:
- Maven
- Docker
- Docker Compose

Append /etc/hosts file:

> `127.0.0.1	elasticsearch-service`
> `127.0.0.1	microservice-mysql`
> `127.0.0.1	microservice-es`


## Testing

### Unit Testing from IDE

- Start Spring Application
- Run as JUnit Test: `src/test/java/feature/FeatureProductSearchIT.java`

### Remote Test against local Spring

> `mvn clean install -Pitest-spring-boot`

### Remote Test against Docker Compose

> `mvn clean install -Pitest-docker-compose`

### Remote Test against Cloud Deployment

> `mvn clean install -Pitest-remote`
