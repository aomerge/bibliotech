# Start whith a base image containing Java runtime
FROM openjdk:17-jdk-alpine
WORKDIR /books-service
# Add a volume pointing to /tmp
VOLUME /tmp

# Make port 8888 available to the world outside this container
EXPOSE 8081

# Copy the .env file into the container
COPY .env /books-service/.env

# The application's jar file
ARG JAR_FILE=target/booksService-0.0.1-SNAPSHOT.jar

# Add the application's jar to the container
ADD ${JAR_FILE} app.jar

# Run the jar file
ENTRYPOINT [ "sh", "-c", "sleep 15 && java -jar /books-service/app.jar" ]
