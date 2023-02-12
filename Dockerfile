FROM openjdk:18
MAINTAINER demo-api
COPY target/demo-0.0.1-SNAPSHOT.jar docker-demo-api-0.0.1.jar
ENTRYPOINT ["java","-jar","/docker-demo-api-0.0.1.jar"]