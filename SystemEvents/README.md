# SystemEvents Service

# Requirements
- Java 13 
- Maven
- Existing SQL database(preferred PostgreSQL 12)
# Guide to run the application

- CREATE database on you server 
- Update connection string in application.properties
- Use command `mvn -T 1C clean install -Dmaven.test.skip=true`
- Use command `mvn spring-boot run` in terminal directed to the service directory 

