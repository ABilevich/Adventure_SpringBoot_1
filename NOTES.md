# Maven

instead of installing maven, we should be able to use ./mvnw

# Running the app from comand line

To Pachage teh app
mvn package

To run we can use either:

- (from the target dir) java -jar demo-0.0.1-SNAPSHOT.jar
- (from the root dir) mvn spring-boot:run

# Spring Boot

## 05 Application Properties File

By default, spring boot reads information fro a standard props file in src/main/resources/application.properties
we can define any props in this file, and we can then access them by using the @Value anotation

A full list of common spring boot related props (port, etc) and be found [here](https://docs.spring.io/spring-boot/appendix/application-properties/index.html)
These props are devided on

- Core
  - logging.level (based on package name)
  - logging.file.name
  - logging.file.path
- Web
  - server.port=7070 (HTTP Server Port)
  - server.servlet.context-path=/my-silly-app (Context parh of the application)
  - server.servlet.session.timeout=15m (Default timout is 30m)
- Security
  - spring.security.user.name=admin
  - spring.security.user.password=securaPassword
- Data
  - spring.datasouurce.url=jdbc:mysql://localhost:3306/ecomerce
  - spring.datasource.username=scot
  - spring.datasource.password=somepass
- Actuator
  - management.endpoints.web.exposure.include (Endpoints to include by name or dildcard)
  - management.endpoints.web.exposure.exclude (Endpoints to exclude by name or dildcard)
  - management.endpoints.web.base-path=/actuator (Base path for atuator endpoints)
- Integration
- DevTools
- Testing
