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

### Technologies
* https://vertx.io
* https://coreos.com/why/
* https://traefik.io
* https://www.consul.io
* https://www.nomadproject.io
* https://prometheus.io
* https://grafana.com
* https://nats.io
* https://www.mongodb.com
* https://superset.apache.org
* https://gitea.io
* https://drone.io
* https://www.zerotier.com
* https://clickhouse.yandex

### Good Practice
* run containers only with selected capabillities, start with --cap-drop=all --cap-add=setuid --cap-add=setgid
* if you use external libraries, sdks or drivers in your verticle, make sure you stay in the verticle context when returning from callback handlers context.runOnContext(v -> {}) (if you fail, you will get these kind of log messages: WARN i.v.c.h.i.ConnectionManager - Reusing a connection with a different context: an HttpClient is probably shared between different Verticles)
* use exceptionHandlers where possible: e.g: httpClient.get(..., res -> res.exceptionHandler(ex -> logger.error(ex.getMessage()))).exceptionHandler(ex -> logger.error(ex.getMessage())).end();
* use camelCase for config files wherever possible
* use use lower case for database collection and table names
* use one of the following words to close issues in gitea automatically (“close”, “closes”, “closed”, “fix”, “fixes”, “fixed”, “resolve”, “resolves”, “resolved”) referencing the issue prefixed with a hash e.g. fixes #1
* deploy verticles with vertx.deployVerticle(CustomVerticle.class.getCanonicalName(), ...) to stay flexible if you want to instanciate it more than once
* if dealing with AsyncResults - always handle succeeded() or failed()
* use only alphabetic characters for eventBus destinations and periods for separation
* use RxHelper.scheduler(vertx) and RxHelper.blockingScheduler(vertx) for rx calls that need a scheduler (e.g. Observable.timer or blockingObservable.observeOn or Observable.delay)
* share binary artefacts via google drive and source code via git
* when consuming the eventbus with rx (localConsumer("").toObservable()), make a retry() part of the pipeline to ensure that the consumer is not lost when an exception occurs during message processing
* always use private final static Logger logger = LoggerFactory.getLogger(<classname>.class);
* stick to semantic versioning
* use clean consistent code seperation over all application parts (config namespaces, verticles, handlers and package names)
* use io.netty.handler.codec.http.QueryStringEncoder for sending query params as client
* add logger.isDebugEnabled() in front of debug statements, if you do .toJson(), .encodePrettily() or any other cpu intensive operations
* do not .gitignore src/main/asciidoc, it will be used for documentation (not used now, but maybe in the future)
* If a Verticle only calls another service (e.g. using ServiceClient) and has otherwise no significant functionality that would use time on the thread, consider building a Client class instead (e.g. WalletClient instead of WalletVerticle). This saves uneccessary event bus messages.
* when returning java-enum values in http-apis as json, the value of the enum item should not be changed (i.e. enum items without a value are ALL_CAPS strings by java convention, enum items with values return the value as it is set). That also means toLowerCase and toUpperCase should not be used.

### Bad Practice
* Do not kill docker containers by default (there is a stop method)
* Do not open fixed ports in unit tests, use dynamic ports so unit tests don’t fail when the port is already open
* Do not use concurrent structures (maps, lists…) in verticles which are by definition threadsafe
* Do not serialize pojo objects to json-string and back to JsonObject, implement toJson() that assembles the JsonObject for all properties (jackson objectMapping slows you down big time)
* Do not construct JsonObject or JsonArray from Map or List because of type safety and potential recursion errors (use json structures directly in your model)
* Do not use timers in unit tests that depend on machine performance (builds may run on slow machines)
* Do not use InetAddress::getByName with a non-ipv4/v6 address (this is a blocking jvm call!)
* Do not assert on content from Exception::getMessage or similar scenarios
* Do not use combinators (anyOf, allOf, oneOf) in openapi3 definitions (refactor the endpoint or document optional/dynamic fields instead)

### URLS
* https://dmp.fabric8.io/#introduction
* https://hub.docker.com/r/azul/zulu-openjdk-alpine
* https://runnable.com/blog/9-common-dockerfile-mistakes
* https://vertx.io/docs/vertx-docker/
* https://www.baeldung.com/rx-java
* https://www.baeldung.com/vertx
* https://github.com/anupsaund/vertx-auto-swagger/blob/master/VertxAutoSwagger/src/main/java/io/vertx/VertxAutoSwagger/MainVerticle.java

* https://stackoverflow.com/questions/19785290/java-unit-testing-how-to-measure-memory-footprint-for-method-call
* https://stackoverflow.com/questions/27831778/how-to-really-benchmark-the-memory-usage-of-a-java-application
* https://github.com/AdoptOpenJDK/jitwatch
* https://gceasy.io/

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
