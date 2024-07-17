# Use a base image that has Java 11 installed
FROM openjdk:11-jre-slim

# Add a volume pointing to /tmp
VOLUME /tmp

# Make port 9091 available to the world outside this container
EXPOSE 9091

# The application's jar file
ARG JAR_FILE=target/demo-0.0.1-SNAPSHOT.jar

# Add the application's jar to the container
ADD ${JAR_FILE} app.jar

# Run the jar file
ENTRYPOINT ["java", "-jar", "/app.jar"]
