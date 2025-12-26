# ===== Stage 1: Build =====
FROM gradle:8.5-jdk21 AS build

WORKDIR /home/gradle/project

# Copy Gradle wrapper and config first (cache-friendly)
COPY gradlew .
COPY gradle gradle
COPY build.gradle settings.gradle ./

# Download dependencies
RUN ./gradlew dependencies --no-daemon

# Copy source code
COPY src src

# Build Spring Boot jar
RUN ./gradlew clean bootJar --no-daemon


# ===== Stage 2: Run =====
FROM eclipse-temurin:21-jre

WORKDIR /app

COPY --from=build /home/gradle/project/build/libs/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar","app.jar"]