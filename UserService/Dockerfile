FROM openjdk:13-alpine
ADD target/UserService-*.jar user-service.jar
EXPOSE 8082
ENTRYPOINT [ "java", "-jar", "user-service.jar" ]