# ----------- Stage 1: Build Stage -----------
FROM maven:3.9.10-eclipse-temurin-21 AS build

# Set working directory inside container
WORKDIR /app

# Copy pom.xml and download dependencies (offline)
COPY pom.xml .
RUN mvn dependency:go-offline

# Copy source files and build the app
COPY src ./src
RUN mvn clean package -DskipTests

# ----------- Stage 2: Run Stage -----------
FROM eclipse-temurin:21-jdk-alpine

# Set working directory
WORKDIR /app

# Copy the built JAR from the build stage
COPY --from=build /app/target/zweibrary-0.0.1-SNAPSHOT.jar app.jar

# Expose the port used by Spring Boot
EXPOSE 8080

# Run the app
ENTRYPOINT ["java", "-jar", "app.jar"]