FROM openjdk:13-alpine
ADD target/user-service.jar user-service.jar
EXPOSE 8080
ENTRYPOINT [ "java", "-jar", "user-service.jar" ]