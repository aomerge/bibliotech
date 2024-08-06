# Start whith a base image containing Java runtime
FROM openjdk:17-jdk-alpine
WORKDIR /config-server
# Add a volume pointing to /tmp
VOLUME /tmp

# Make port 8888 available to the world outside this container
EXPOSE 8888

# Copy the .env file into the container
COPY .env /config-server/.env

# Copiar el directorio de configuración al contenedor
COPY config /config-server/config

# The application's jar file
ARG JAR_FILE=target/configServer-0.0.1-SNAPSHOT.jar

# Add the application's jar to the container
ADD ${JAR_FILE} app.jar

# Run the jar file
ENTRYPOINT ["java","-jar","/config-server/app.jar"]

