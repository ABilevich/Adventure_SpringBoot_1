# To run using vs code

Open any 0X numbered folder directly, not the root folder (the one with the readme)

# Maven

instead of installing maven, we should be able to use ./mvnw

# Running the app from command line

To Package teh app
mvn package

To run we can use either:

- (from the target dir) java -jar demo-0.0.1-SNAPSHOT.jar
- (from the root dir) mvn spring-boot:run

# 0 Basic Spring config

## 0.5 Application Properties File

By default, spring boot reads information fro a standard props file in src/main/resources/application.properties
we can define any props in this file, and we can then access them by using the @Value annotation

A full list of common spring boot related props (port, etc) and be found [here](https://docs.spring.io/spring-boot/appendix/application-properties/index.html)
These props are divided on

- Core
  - logging.level (based on package name)
  - logging.file.name
  - logging.file.path
- Web
  - server.port=7070 (HTTP Server Port)
  - server.servlet.context-path=/my-silly-app (Context path of the application)
  - server.servlet.session.timeout=15m (Default timeout is 30m)
- Security
  - spring.security.user.name=admin
  - spring.security.user.password=securePassword
- Data
  - spring.datasource.url=jdbc:mysql://localhost:3306/database
  - spring.datasource.username=scot
  - spring.datasource.password=something
- Actuator
  - management.endpoints.web.exposure.include (Endpoints to include by name or wildcard)
  - management.endpoints.web.exposure.exclude (Endpoints to exclude by name or wildcard)
  - management.endpoints.web.base-path=/actuator (Base path for actuator endpoints)
- Integration
- DevTools
- Testing

# 1 Inversion of control (IoC) and the Spring Container

Its the approach of outsourcing the construction and management of objects
Spring container works as an object factory
it has two primary functions

- Create and manage objects (inversion of control)
- Inject object dependencies (Dependency Injection)

we can configure it with XML (Legacy), Java annotations (Modern) or Java source Code (Modern)

## 1.1 Dependency injection

The client delegates to another object the responsibility of providing its dependencies

Types

- Constructor injections
  - Use when you have required dependencies (recommended as first choice)
- Setter injections
  - Use when you have optional dependencies (the app should work with default logic if this dependency is not provided)

Autowiring

- For dependency injection, spring can use autowiring
- Spring will look for a class that matches and will inject it automatically
  - By type: class or interface
- If you want to inject a Coach implementation
  - Spring will scan for @Components (marked as spring beans)
  - Any one implements the Coach Interface?
  - If so, lets inject them, for example CricketCoach

Development process - using constructor injection

1. Define the dependency interface and class
2. create Demo REST controller
3. create a constructor in the class for injections
4. add @GetMapping for /dailyWorkout

@Component annotation

- Marks the class as a Spring Bean
  - is a regular java class that is managed by spring
- Makes the component available for dependency injection

## 1.2 Controller Injection

- Spring is doing
  - Coach theCoach = new CricketCoach()
  - DemoController demoController = new DemoController(theCoach)

### Component Scanning

- Spring will scan you java classes for special annotations (@Component, etc)
- It will automatically register the beans in the spring container

Annotations (That compose @SpringBootApplication)
@EnableAutoConfiguration: Enables spring boot's auto-configuration support
@ComponentScan: Enables component scanning of current package (also recursively scans sub packages)
@Configuration: Able to register extra beans with @Bean or import other configuration classes

- Spring will scan any packages under the main folder recursively, they all need to be inside the springcoredemo package in this case
- This can be overwritten on the @SpringBootApplication annotation by specifying the scanBasePackages to include aditional packages

## 1.3 Setter injection

Its when we inject dependencies by calling the setter methods on our class

To Inject the catch implementation

1. Spring will scan for @Components
2. Does anyone implement the Coach interface?
3. If so, lets inject them, for example CricketCoach

Development process - using setter injection

1. Create setter method(s) in your class for injections
2. configure the dependency injection with @Autowired Annotation

- Spring is doing
  - Coach theCoach = new CricketCoach()
  - DemoController demoController = new DemoController()
  - demoController.setCoach(theCoach)

## 1.4 Field injection with annotations and Autowiring

Field injections are not recommended by the spring dev team
it makes the code harder to unit test
its accomplished by using reflection
it works by putting the @Autowired prop on top of prop field itself

## 1.5 Autowiring and Qualifiers

what happens if we have multiple implementations for an autowired prop

we can specify the exact implementation we want by using the @Qualifier Annotation
@Qualifier("cricketCoach")
here we specify with the bean name, which is the same as the class name but with camelcase
for setter injection we can use the same @Qualifier

## 1.6 Primary

we can also solve the issue with the @Primary annotation

if using both annotations, qualifier has a higher priority

## 1.7 Lazy initialization

by default all beans are initialized on application start, spring creates an instance of each one and makes it available

if lazy initialization is used, a bean will only be initialized if its needed for dependency injection, or its explicitly requested

This is accomplished by using the @Lazy annotation

we can setup
spring.main.lazy-initialization=true

with this setting, all beans are lazy an no been created until its needed

The advantages are speed, it has some problems for @RestControllers and there may be configurations that don't fail until its too late,

its better no not do early optimization in this case.

## 1.8 Bean scope

the default scope is singleton (only one instance is created)

we could specify the scope in the class itself

- singleton: Create a single shared instance of the bean (Default)
- prototype: Creates a new bean instance for each container request

And some scopes used only for web apps

- request: Scoped to an HTTP web request
- session: Scoped to an HTTP web session
- application: Scoped to a web app ServletContext
- websocket: Scoped to a web socket

## 1.9 Bean Lifecycle method

1. Container Start
2. Bean Instantiated
3. Dependency injection
4. Internal Spring processing
5. Your custom init method
6. Bean is ready to use
7. Container is shutdown
8. Your custom destroy method
9. Stop

with @PostConstruct we can add custom init code that runs after the bean is constructed
with @PreDestroy we can specify things to happen once the bean is destroyed

Note: if the prototype scope is used, we cant access the @PreDestroy lifecycle step, since spring hands the reference to the client after its initialized

## 1.10 Java Code Config

1. Create @Configuration class
2. Define @Bean method to configure the bean
3. Inject the bean into our controller

why not just use the @Component?

Use Case for @Bean
the main use case is when you cant to make an existing third-party class available to the spring framework. You may not have access to the source code of the third-party class, However, you would like to use it as a Spring Bean

we can give the @Bean a custom name by specifying it inside the annotation, if not, its the function name (camelCase)

# 3 Hibernate and JPA

Hibernate its a framework for persisting objects in a database

- It handles the ORM and all low level SQL queries
- We map a Java Class to a Database Table

JPA (Jakarta Persistence API) is a standard api for object to relational mapping that defines a set of interfaces that require an implementation to be usable

Hibernate is an implementation of the JPA Spec, EclipseLink is another

By having a standard API we are not locked to the vendor

to save an object we create it and then use
entityManager.persist(someObject)

## 3.1 Hibernate JPA and JDBC

JDBC is still used, Hibernate making use of it to communicate with the database

Spring will automatically inject the beans for DataSource, EntityManager, etc

## 3.2 MySql

Its a standard rest DB that comes with a workbench that has a client GUI

we need to add the connection dettails on the application.properties
