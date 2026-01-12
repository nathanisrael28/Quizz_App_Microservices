## Stage 1: Build
#FROM maven:3.9.6-eclipse-temurin-21 AS builder
#WORKDIR /app
#COPY . .
#RUN mvn clean package -DskipTests
#
## Stage 2: Run
#FROM eclipse-temurin:21-jre-alpine
#WORKDIR /app
#COPY --from=builder /app/target/api-gateway.jar api-gateway.jar
#
##COPY target/api-gateway.jar api-gateway.jar
#ENTRYPOINT ["java","-jar","api-gateway.jar"]


# Stage 1 — Build
FROM maven:3.9.6-eclipse-temurin-21 AS builder
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Stage 2 — Run
FROM eclipse-temurin:21-jdk-alpine
WORKDIR /app
COPY --from=builder /app/target/*.jar app.jar
ENTRYPOINT ["java","-jar","app.jar"]
