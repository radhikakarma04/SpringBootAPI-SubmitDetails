# Step 1: Build the application using Maven with Java 8
FROM maven:3.8.5-openjdk-8 AS build
WORKDIR /app

# Copy Maven config and source code
COPY pom.xml .
COPY src ./src

# Build the JAR
RUN mvn clean package -DskipTests

# Step 2: Use lightweight Java 8 image to run the built app
FROM openjdk:8-jdk-alpine
WORKDIR /app

# Copy only the generated JAR file to the container
COPY --from=build /app/target/journalApp-0.0.1-SNAPSHOT.jar app.jar

# Expose Spring Boot default port
EXPOSE 8080

# Command to run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
