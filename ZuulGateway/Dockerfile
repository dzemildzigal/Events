FROM openjdk:13-alpine
ADD target/ZuulGateway-*.jar zuul-gateway.jar
EXPOSE 8082
ENTRYPOINT [ "java", "-jar", "zuul-gateway.jar" ]