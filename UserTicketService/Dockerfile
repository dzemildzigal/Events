FROM openjdk:13-alpine
ADD target/UserTicketService-*.jar user-ticket-service.jar
EXPOSE 8082
ENTRYPOINT [ "java", "-jar", "user-ticket-service.jar" ]