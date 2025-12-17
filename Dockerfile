# --- STAGE 1: Build the Application ---
FROM maven:3.9-eclipse-temurin-17 AS build
WORKDIR /app

# Copy the project files
COPY pom.xml .
COPY src ./src

# Build the app inside the container
RUN mvn clean package -DskipTests

# --- STAGE 2: Run the Application ---
# We use eclipse-temurin here instead of the broken 'openjdk' image
FROM eclipse-temurin:17-jdk-jammy
WORKDIR /app

# Copy the JAR file from the build stage
COPY --from=build /app/target/*.jar app.jar

# Expose the port
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]