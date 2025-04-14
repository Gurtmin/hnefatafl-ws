# Fáze 1: Build aplikace
FROM gradle:7.6.0-jdk17 AS build
WORKDIR /app
COPY . .
RUN gradle bootJar --no-daemon --stacktrace

# Fáze 2: Spuštění aplikace
FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=build /app/build/libs/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
