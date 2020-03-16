**Creating Spring boot docker image**

**Dockerfile**
```aidl
FROM openjdk:8-jdk-alpine
EXPOSE 9090
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
```
**Build image**
````aidl
> docker build -t springio/greeting .
````
**Run the docker image**
```aidl
> docker run -p 9090:9090 e799f6167feb

```
>Running applications with user privileges helps to mitigate some risks (see for example a thread on StackExchange). So, an important improvement to the Dockerfile is to run the app as a non-root user:
>**Dockerfile**
```aidl
FROM openjdk:8-jdk-alpine
RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
```
>Also, to take advantage of the clean separation between dependencies and application resources in a Spring Boot fat jar file, we will use a slightly different implementation of the Dockerfile:
```aidl
 > mkdir -p target/dependency && (cd target/dependency; jar -xf ../*.jar)

```
>>**Dockerfile**
```aidl
FROM openjdk:8-jdk-alpine
RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring
ARG DEPENDENCY=target/dependency
COPY ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY ${DEPENDENCY}/META-INF /app/META-INF
COPY ${DEPENDENCY}/BOOT-INF/classes /app
ENTRYPOINT ["java","-cp","app:app/lib/*","hello.Application"]
```





