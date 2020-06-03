FROM openjdk:13-alpine
ADD target/EventService-*.jar event-service.jar
EXPOSE 8082
ENTRYPOINT [ "java", "-jar", "event-service.jar" ]