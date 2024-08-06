# Start whith a base image containing Java runtime
FROM openjdk:17-jdk-alpine
WORKDIR /app
# Add a volume pointing to /tmp
VOLUME /tmp

# Make port 8888 available to the world outside this container
EXPOSE 8083

# Copy the .env file into the container
COPY .env /app/.env

# The application's jar file
ARG JAR_FILE=target/*.jar

# Add the application's jar to the container
ADD ${JAR_FILE} app.jar

# Run the jar file
ENTRYPOINT ["java","-jar","/app/app.jar"]