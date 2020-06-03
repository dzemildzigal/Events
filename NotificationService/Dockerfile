FROM openjdk:13-alpine
ADD target/NotificationService-*.jar notification-service.jar
EXPOSE 8082
ENTRYPOINT [ "java", "-jar", "notification-service.jar" ]