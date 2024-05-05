# This Dockerfile is used to build a Docker image for the backend of the project.
# It starts with the base image "eclipse-temurin:17-jdk-focal" which provides the Java 17 JDK.
# The working directory is set to "/app".

FROM eclipse-temurin:17-jdk-focal 
WORKDIR /app
 
# The following lines copy the Maven wrapper files and the project's pom.xml file to the working directory.
# Then, it runs the "./mvnw dependency:go-offline" command to download all the project dependencies.

COPY .mvn/ .mvn
COPY mvnw pom.xml ./
RUN ./mvnw dependency:go-offline
 
# The "src" directory containing the project source code is copied to the working directory.

COPY src ./src

# The "input" directory containing input files for the project is copied to the working directory.

COPY input ./input