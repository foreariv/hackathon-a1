# --- STAGE 1: Build Stage ---
# Use a Maven base image with JDK 21
FROM maven:3.9.6-eclipse-temurin-21-alpine AS build

WORKDIR /app
COPY pom.xml .
COPY src /app/src

# Package the application into a JAR file
RUN mvn clean package -DskipTests

# --- STAGE 2: Runtime Stage ---
# Use a minimal JRE 21 image for the final container
FROM eclipse-temurin:21-jre-alpine

# Set the working directory
WORKDIR /app

# The service will run as a non-root user for security (recommended for Cloud Run)
USER nonroot

# Copy the JAR file from the build stage
COPY --from=build /app/target/*.jar app.jar

# Define the port Cloud Run will expose
EXPOSE 8080

# Command to run the application
ENTRYPOINT ["java", "-jar", "app.jar"]