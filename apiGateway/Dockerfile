
# Etapa de construcción
#FROM maven:3.8.8-eclipse-temurin-17 AS build
#WORKDIR /home/maven/project
#COPY . .
#RUN mvn clean package -DskipTests
# Etapa final
FROM openjdk:17-jdk-alpine

WORKDIR /app
# Add a volume pointing to /tmp
VOLUME /tmp

# Make port 8080 available to the world outside this container
EXPOSE 8080

# Copy the .env file into the container
COPY .env /app/.env

# The application's jar file
ARG JAR_FILE=target/*.jar

# Add the application's jar to the container
ADD ${JAR_FILE} app.jar

# Definir un único ENTRYPOINT
ENTRYPOINT ["sh","-c", "sleep 25 && java -jar /app/app.jar"]
