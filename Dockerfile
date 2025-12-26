# ===== Stage 1: Build =====
FROM gradle:8.5-jdk17-alpine AS build

WORKDIR /home/gradle/project

# Copy gradle files first (better cache)
COPY build.gradle settings.gradle gradlew ./
COPY gradle gradle

# Download dependencies (cached layer)
RUN ./gradlew dependencies --no-daemon

# Copy source code
COPY src src

# Build the application
RUN ./gradlew clean bootJar --no-daemon


# ===== Stage 2: Run =====
FROM eclipse-temurin:17-jdk-alpine

WORKDIR /app

# Copy jar from build stage
COPY --from=build /home/gradle/project/build/libs/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar","app.jar"]
