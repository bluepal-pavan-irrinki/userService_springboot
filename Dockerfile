# Use the official Maven image to create a build artifact.
FROM maven:3.8.4-openjdk-17 AS build
WORKDIR /app

# Copy the pom.xml and install dependencies
COPY pom.xml .
RUN mvn dependency:go-offline

# Copy the source code
COPY src ./src

# Build the application
RUN mvn clean package -DskipTests

# Use the official OpenJDK image to run the application.
FROM openjdk:17-jre-slim
WORKDIR /app

# Copy the built jar from the build stage
COPY --from=build /app/target/demo-0.0.1-SNAPSHOT.jar app.jar

# Expose the application port
EXPOSE 9091

# Define the entry point for the container
ENTRYPOINT ["java", "-jar", "app.jar"]
