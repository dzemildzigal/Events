# User Service

# Requirements
- Java 13 
- Maven
- Existing SQL database(preferred PostgreSQL 12)
# Guide to run the application

- This Guide assumes that PostgreSQL 12 is installed on the machine that you wish to run the application on.
- Login to the PostgreSQL 12 Admin database interface (on your preffered web browser) using your superuser/admin account priviledges that you set up on install of PostgreSQL 12.
- CREATE a database by the name of EventDatabase on you server using the PostgreSQL 12 Admin database interface mentioned in the previous step.
- Update the connection strings of the username and password for the database in application.properties included in the Service's project files to your superuser/admin credentials of PostgreSQL.
- Use command `mvn spring-boot run` in a terminal by your choice while being directed in the EventService directory 
- To run tests use  `mvn test`  in terminal directed to the service directory 
- Important notice: EventService is a part of a microservice architecture by the name of Events. To that extent, Events/UserService, Events/NotificationService, Events/EurekaService must be running (on their respective ports) alongside it to ensure that all the tests pass. If the tests fail, ensure that these microservices are running and available for EvenService to communicate with them.
