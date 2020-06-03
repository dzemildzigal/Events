FROM openjdk:13-alpine
ADD target/zuul-gateway.jar zuul-gateway.jar
EXPOSE 8090
ENTRYPOINT [ "java", "-jar", "zuul-gateway.jar" ]