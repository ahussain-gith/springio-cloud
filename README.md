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





