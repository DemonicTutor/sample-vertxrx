# Vertx Rx-Java

image:https://img.shields.io/badge/vert.x-3.8.4-purple.svg[link="https://vertx.io"]

This application was generated using http://start.vertx.io

## Building

To launch your tests:
```
./mvnw clean test
```

To package your application:
```
./mvnw clean package
```

To run your application:
```
./mvnw clean compile exec:java
```

## My Documentation

### URLS
* https://dmp.fabric8.io/#introduction
* https://hub.docker.com/r/azul/zulu-openjdk-alpine
* https://runnable.com/blog/9-common-dockerfile-mistakes
* https://vertx.io/docs/vertx-docker/
* https://www.baeldung.com/rx-java
* https://www.baeldung.com/vertx
* https://github.com/anupsaund/vertx-auto-swagger/blob/master/VertxAutoSwagger/src/main/java/io/vertx/VertxAutoSwagger/MainVerticle.java

### docker

Docker start
```
docker run --rm -p 127.0.0.1:8080:8080/tcp --name sample-vertxrx-service docker-registry.demonworld.org/sample-vertxrx-service
```
Interactive run
```
docker run -it --rm -p 127.0.0.1:8080:8080/tcp docker-registry.demonworld.org/sample-vertxrx-service sh
```
Override entrypoint
```
docker run -it --rm -p 127.0.0.1:8080:8080/tcp --entrypoint sh docker-registry.demonworld.org/sample-vertxrx-service
```

### mvn
```
mvn clean install
mvn -pl sample-vertxrx-servicetest docker:start
mvn -pl sample-vertxrx-servicetest docker:stop
```

#### maven-versions-plugin
```
mvn versions:display-dependency-updates
mvn versions:display-plugin-updates
mvn versions:display-property-updates
```
#### maven-dependency-plugin
```
mvn dependency:sources dependency:resolve -Dclassifier=javadoc
