Problems with monolithic:-
- Difficult to implement changes
- Lack of scalibility
- Long term commitment to a single technology stack
- Application complexity and its effect on development and deployment
- Slowing down of IDEs
- Increased Application Start time
- Large Project Size
- Deploying for small changes
- Team Collaboration and Autonomy

Microservices structures an application as a collection of small autonomous services

Principles of Microservices:-
- Single Responsibility
- Independence
- Decentralisation
- Failure Isolation
- Continuous Delivery/Deployment

Rest template is used to communicate between 2 micro services
Advantages of rest template:-
- Abstraction
- Versatility
- Conversion
- Error Handling
- Integeration

DTO (Data Transfer Object) Pattern: - Design Pattern used to transfer data between software application subsystems

Service Registry:- Service Registry is used in microservices architectures to enable dynamic service discovery
Benefits of service registry:-
- Dynamic Service Discovery
- Load Balancing
- Fault Tolerance and Resilience
- Scalability and Elasticity
- Service Monitoring and Health Checks
For service Registration and discovery server in distributed cloud systems Spring cloud eureka by Netflix is used

To register a service to eureka service the heartbeat mechanism is followed.

OpenFeign:-
Feign:- Feign is a declarative we service designed to make writing HTTP clients easier.
Why use Open Feign:-
- Easy to Use
- Integration with Eureka
- Built in Load Balancing with Ribbon
- Support for Fallbacks and Circuit Breakers

How would you track a request from start to end?
Distributed tracing enables you to trace your request from start to end

Problems with Distributed tracing:-
- Request Visualisation
- Identify performance Bottleneck
- Error Analysis and debugging
- Tracking dependency
- Performance Optimisation

Pipkin is the Distributed Tracing System.

Micrometer:- Micrometer provides insights that help you keep tabs on your application’s performance
How Micrometer helps:-
- Helps to collect metrics from the application
- Acts as a middlemen or a bridge between the application and the metrics collection systems
- It offers a vendor neutral interface
- You can not abstract away the complexities of interacting with different collection systems

Configuration Management
This means that managing and controlling the configurations of each micro service in the system.
Configuration may include details such as database connections, external service URLs, caching settings and more
Challenge:-As the number of micro services increases in the architecture, managing the individual configurations can become a complex task.
A Config server provides a central place for managing configurations across all microservices.
This simplifies the configuration management and increases operational efficiency.

Features of Config Server:-
- Centralised and Versioned Configuration
- Dynamic Updates
- Security
- Application and Profile Specific Configuration

Benefits of Config Server:-
- Single Source of truth
- Easier to manage and update configurations
- Enhance security and control
- Easy to deploy and scale microservices

Spring Cloud Config Server:-
Spring Cloud Config Server is part of the Spring Cloud project, a suite of tools specifically designed for building and managing cloud native applications.
Microservices  ——> Spring Cloud Config Server ——> Git

Benefits of Spring Cloud Config Server:-
- Storing Configurations
- Serving Configurations
- Refreshing Configurations
- Easy integration of Spring Boot
- Support for different environments
- Encryption and Decryption

Microservices Architecture:- A monolithic architecture is broken down into smaller, loosely coupled services

API Gateway advantages:-
- Encapsulation:- It encapsulates the internal system architecture 
- Handle cross cutting  concerns like security, load balancing, rate limiting and analytics
- Can authenticate incoming requests and pass only valid requests to the services
- Can aggregate responses from different  microservices
- Plays a crucial role in simplifying client interactions and managing cross cutting concerns

API Gateway Functions:-
Capabilities of API Gateway:-
- Request Routing
- Load Balancing
- Authentication and Authorization
- Rate Limiting
- Request and response transformation
- Aggregation of data from multiple services
We implement API Gateway using Spring Cloud Gateway
Spring Cloud Gateway


Fault Tolerance:- Ability to continue operating without interruption
Need for Fault Tolerance:-
* Falut Isolation
* Network Latency
* Deployment Issues
* Increased Complexity
* Elasticity
* Tolerating External Failures

Resilience:- Ability or capacity to recover quickly from difficulties.
Techinques for resilience:-
- Retries
- Rate Limiting
- Bulkheads
- Circuit Breakers
- Fallbacks
- Timeouts
- Graceful Degradation

Resilience4J is a lightweight, easy-to-use fault tolerance library.
Introduction
Resilience4J Modules:-
- Retry Module
- Rate Limiter
- BulkHead
- CircuitBreaker

Retry Module:-
* It is not uncommon for network call or a method invocation to fail temporarily.
* We might want to retry the operation few times before giving up.
* Retry module enables to easily implement retry logic in our applications

RateLimiter:-
* We might have a service which can handle only a certain number of requests in a given time
* RateLimiter Module allows us to enforce restrictions and protect our services from too many requests

Bulkhead:-
* Isolates failures and prevents them from cascading through the system.
* Limit the amount of parallel executions or concurrent call to prevent system resources from being exhausted.

CircuitBreaker:-
* Used to prevent a network service failure from cascading to other services.
* CircuitBreaker ’trips’ or opens and prevents further calls to the service

Rate Limiting:- Rate Limiting is a technique for limiting network traffic
Importance of Rate Limiting:-
* Preventing Abuse
* Resource Allocation
* Cost Management

Use-cases of Rate Limiting:-
* APIs
* Web Scrapping
* Login Attempts
* Prevent fromDDOS(Distributed Denial Of Service) attacks

Message Queues:- Message queues play a crucial role in microservices architecture to facilitate communication and data exchange between services 

 Need for Message queues:
* Decoupling
* Asynchronous Communication 
* Scalability
* Fault Tolerance:- if the service is down the message is stored in message queue ensuring they will be processed once the service is up
* Event Driven Architecture
* Time Decoupling

Message queue is a form of asynchronous service to service communication used in server less and microservices architectures

Producer ———> Message Queue ———> Consumer

RabbitMQ, Apache Kafka, Amazon SQS, ActiveMQ are the best MQ.
RabbitMQ is openSource


Execution of Spring Boot Application:-
* Code Compilation
* Running the Main Class
* Classpath and Dependencies
* Embedded server
* Source Code Changes
* Development Mode

What is Packaging?
Packaging involves compiling your source code into byte code, bundling it with any dependent libraries and creating a single executable artifact that can be easily distributed and run

Package = Bytecode + Dependent Libraries + Configuration = JAR(Java Archive)

Benefits of JAR:-
* Simplified Deployment
* Inclusion of everything our application needs
* JRE executes JAR files

Other packaging Options:-  WAR, EAR, Docker image

