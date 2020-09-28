# For Java 8, try this
# FROM openjdk:8-jdk-alpine

# For Java 11, try this
FROM adoptopenjdk/openjdk11:alpine-jre

RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring

# Refer to Maven build -> finalName
ARG JAR_FILE=target/*.jar

# cp target/spring-boot-web.jar /opt/app/app.jar
COPY ${JAR_FILE} /opt/app/application.jar

ENTRYPOINT ["java","-jar","/opt/app/application.jar"]
