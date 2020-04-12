# User Ticket Service

# Requirements
- Java 13 
- Meaven
- Existing SQL database(prefered PostgreSQL 12)
# Guide to run the application

- CREATE database on you server 
- Update connection string in application.properties
- Use command `mvn spring-boot run` in terminal directed to the service directory (or use IDE like IntelliJ) 
- To run tests first run EurekaService, UserService, EventService because we use their endpoints for communication in the tests. 
   Then enter  `mvn test` command  in terminal directed to the service directory (or use IDE like IntelliJ)

