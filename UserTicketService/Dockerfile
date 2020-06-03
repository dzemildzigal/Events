FROM openjdk:13-alpine
ADD target/user-ticket-service.jar user-ticket-service.jar
EXPOSE 8081
ENTRYPOINT [ "java", "-jar", "user-ticket-service.jar" ]