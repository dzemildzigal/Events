# Notification Service

# Requirements
- Java 13 
- Maven
- Existing SQL database(preferred PostgreSQL 12)
# Guide to run the application
- This Guide assumes that PostgreSQL 12 is installed on the machine that you wish to run the application on.
- Login to the PostgreSQL 12 Admin database interface using your admin account.
- CREATE a database with the name : "NotificationServiceDatabase" on you server.
- Update the connection strings of the username and password for the database in application.properties included in the Service's project files to your superuser/admin credentials of PostgreSQL.
- Use command `mvn spring-boot run` in a terminal while being directed in the NotificationService directory 
- To run tests use  `mvn test`  in terminal directed to the service directory 
- Important notice: EventService is a part of a microservice architecture by the name of Events. To that extent, Events/UserService,Events/EventService, Events/EurekaService must be running (on their respective ports) as well to ensure that all the tests pass. 
