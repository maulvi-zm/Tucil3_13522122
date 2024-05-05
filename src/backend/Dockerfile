# This Dockerfile is used to build a Docker image for the backend of the application.

# Use the eclipse-temurin:17-jdk-focal base image
FROM eclipse-temurin:17-jdk-focal 

# Set the working directory inside the container to /app
WORKDIR /app

# Copy the .mvn/ directory from the host to the container
COPY .mvn/ .mvn

# Copy the mvnw and pom.xml files from the host to the container
COPY mvnw pom.xml ./

# Run the Maven command to download the project dependencies
RUN ./mvnw dependency:go-offline

# Copy the src/ directory from the host to the container
COPY src ./src

# Copy the input/ directory from the host to the container
COPY input ./input