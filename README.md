# Helpdesk System
Description
----------
The Helpdesk System is designed to optimize the management of technical support requests in telecommunications companies.

Customer service agents can register support tickets based on phone calls received from clients. Clients can then track the status of their requests and receive updates through REST APIs. The system stores all data in a relational database and provides reporting on average resolution times and technician workload.

Functional Requirements
----------
1. The system supports the registration, assignment, and tracking of technical support requests for a telecom company's clients.
2. Ticket registration is performed by customer service agents upon receiving phone calls from clients.
3. For each ticket, the system records customer details, the phone line number with issues, and a description of the problem.
4. Tickets are categorized (e.g., billing, connectivity, telephony, connection speed).
5. The system automatically assigns tickets to appropriate engineers based on their specialization and current workload, aiming for balanced distribution.
6. Each engineer is qualified to handle specific ticket categories according to their expertise.
7. The admin team maps engineer specialties to ticket categories and assigns specialties to engineers in coordination with HR.
8. During ticket resolution, engineers record their actions, classified as:
   (a) Technical tasks and (b) Customer communications.
9. Each action includes a title, description, and execution date.
10. For customer communications, the system also records the call duration.
11. Once a ticket is resolved, the customer is notified via email.
12. The system generates reports for management, such as:
    - Average number of communications per ticket category
    - Number of tickets per month

Development & Build
----------
The system was developed using an iterative and incremental software development process.

This version is built using the **Spring Boot** framework.  
It uses JPA and Hibernate as the JPA provider to access the relational database.  
REST services are implemented with the Spring MVC (Web) module.

The application uses MapStruct for DTO mapping, and Hibernate Validator for data validation.
Automated tests are written using JUnit 5 and Mockito.  
Spring Boot Test provides an embedded testing environment with H2 in-memory database.

Note: The system currently does not provide a graphical or user-facing interface.  
Functionality is demonstrated through REST service calls, tested via automated scenarios and tools like Postman.

Build Requirements
----------
- Java 21
- Maven 3.9.9+

Build Commands
----------
To build the application and run tests, use:

```
mvn clean install
```

To run the application:

```
mvn spring-boot:run
```

Documentation
----------
Documentation is written in Markdown. UML diagrams are created using UMLet.

Under the `/docs` folder, you will find folders containing UML diagram sources, images, and markdown documentation files.

More information about the domain model can be found here:  
[Domain Model Diagram](docs/MD_Files/Domain_Model.md)

Author
----------
Christos Bampoulis  
GitHub: [@ChristosBaboulis](https://github.com/ChristosBaboulis)

Acknowledgments
----------
This project was developed as part of an academic assignment during my MSc studies, in the context of the course "Advanced Methods for Software Development".  
It demonstrates the design and implementation of a structured helpdesk ticketing workflow using Spring Boot and modern backend development practices.
