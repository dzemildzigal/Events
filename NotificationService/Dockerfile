FROM openjdk:13-alpine
ADD target/notification-service.jar notification-service.jar
EXPOSE 8083
ENTRYPOINT [ "java", "-jar", "notification-service.jar" ]