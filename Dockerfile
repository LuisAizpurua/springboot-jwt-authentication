FROM maven:3.9.6-eclipse-temurin-21 AS build
WORKDIR /app

COPY . .

RUN chmod +x ./mvnw \
    && ./mvnw dependency:go-offline \
    && ./mvnw clean package -DskipTests

FROM openjdk:21-slim

WORKDIR /app

COPY --from=build /app/target/security-0.0.1-SNAPSHOT.jar app.jar
COPY .env .env

#ENV PROFILE=prod \
#    DB_HOST=localhost \
#    PORT=8090

EXPOSE 8090

CMD ["java", "-jar","app.jar"]