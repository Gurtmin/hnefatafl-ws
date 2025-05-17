# Fáze 1: Build aplikace pomocí Gradle
FROM gradle:7.6.1-jdk17 AS build
WORKDIR /build
COPY . .
RUN gradle bootJar --no-daemon --stacktrace

# Fáze 2: Spuštění aplikace s menším a bezpečnějším image
FROM eclipse-temurin:17-jdk-alpine

# Vytvoření ne-root uživatele
RUN addgroup -S appgroup && adduser -S appuser -G appgroup
USER appuser

WORKDIR /app

# Zkopíruj výsledný JAR ze stage "build"
COPY --from=build /build/build/libs/*-boot.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
