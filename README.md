# To run using vs code

Open any 0X numbered folder directly, not the root folder (the one with the readme)

# Maven

instead of installing maven, we should be able to use ./mvnw

# Running the app from comand line

To Pachage teh app
mvn package

To run we can use either:

- (from the target dir) java -jar demo-0.0.1-SNAPSHOT.jar
- (from the root dir) mvn spring-boot:run

# 0 Basic Spring config

## 0.5 Application Properties File

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

# 1 Inversion of controll (IoC) and the Spring Container

Its the approach of outsourcing the constructoin and management of objects
Spring container works as an object factory
it has two primary functions

- Create and manage objects (inversoin of controll)
- Inject object dependencies (Dependency Injection)

we can configure it with XML (Legacy), Java anotations (Modern) or Java source Code (Modern)

## 1.1 Dependency injectoin

The client dleegates to another object the responsability of providing its dependencies

Types

- Constructure injections
  - Use when you have required dependencies (recomended as first choice)
- Setter injections
  - Use when you hvae optional dependencies (the app should work with default logic if this dependency is not provided)

Autowiring

- For dependency injectoin, spring can use autowiring
- Spring will look for a class that mathes and will inject it automaticaly
  - By tipe: class or interface
- If you want to inject a Coach implementation
  - Sping will scan for @Components (marked as spring beans)
  - Any one implements the Coach Interface?
  - If so, lets inject them, for exmaple CricketCoach

Development process - using constructor injection

1. Define the dependency interface and class
2. create Demo REST controller
3. create a constructor in the class for injections
4. add @GetMapping for /dailyWorkout

@Component annotation

- Marks the class as a Spring Bean
  - is a regular java class that is managed by pring
- Makes the component available for dependency injection

## 1.2 Controller Injection

- Spring is doing
  - Coach theCoach = new CricketCoach()
  - DemoController demoController = new DemoController(theCoach)

### Component Scanning

- Spring will scann you java classes for special anotations (@Component, etc)
- It will automatically register the beans in the spring container

Anotations (That componse @SpringBootApplication)
@EnableAutoConfiguration: Enables spring boot's auto-configuration support
@ComponentScan: Enables component scanning of current package (also recurseively scans sub packages)
@Configuration: Able to register extra beans with @Bean or import other configuration classes

- Spring will scann any packages under the main folder recursively, they all need to be inside the springcoredemo package in this case
- This can be overwritten on the @SpringBootApplication anotation by specifiyong the scanBasePackages to include aditional packages

## 1.3 Setter injection

Its when we inject dependencies by calling the setter methods on our class

To Inject the cach implementation

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

Field injections are not recomended by the spring dev team
it makes the code harder to unit test
its acomplished by using reflection
it works by putting the @Autowired prop on top of prop field iteslf

## 1.5 Autowiring and Qualifiers

what happens if we have multiple implementations for an autowired prop

we can specify the exact implementation we want by using the @Qualifier Annotation
@Qualifier("cricketCoack")
here we specify with the bean name, wich is the same as the class name but with camelcase
for setter injection we can use the same @Qualifier

we can also solve the issue with the @Primary anotation
