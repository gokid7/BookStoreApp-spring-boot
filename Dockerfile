FROM openjdk:17-jdk-slim

ARG JAR_FILE=target/tBookStoreApp-0.0.1-SNAPSHOT.jar

WORKDIR /opt/app

COPY ${JAR_FILE} BookStoreApp.jar

ENTRYPOINT ["java","-jar","BookStoreApp.jar"]