
# Etapa de construcción
FROM maven:3.8.8-eclipse-temurin-17 AS build
WORKDIR /home/maven/project
COPY . .
RUN mvn clean package -DskipTests

# Etapa final
FROM eclipse-temurin:17
WORKDIR /my-project

# Copiar el archivo .env al contenedor (si es necesario)
COPY .env ../.env

# Copiar el archivo JAR desde la etapa de construcción
COPY --from=build /home/maven/project/target/*.jar app.jar

# Definir un único ENTRYPOINT
ENTRYPOINT ["java", "-jar", "app.jar"]


