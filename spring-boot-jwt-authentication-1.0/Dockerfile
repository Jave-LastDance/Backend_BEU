FROM openjdk:17-jdk-alpine AS build
WORKDIR /app
ENV DATABASE_IP=192.168.76.110:3306
ENV DB_DATABASE=usersbeu
ENV DB_USER=root
ENV DB_PASSWORD=BeuAdmin23
ENV PORT=8092
COPY . .
RUN mvn install
RUN mvn clean package

FROM eclipse-temurin:latest
COPY target/demo-jwt-0.0.1-SNAPSHOT.jar .
ENTRYPOINT [ "java", "-jar","demo-jwt-0.0.1-SNAPSHOT.jar"]