# Étape 1 : build du jar
FROM maven:3.9.0-eclipse-temurin-17 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Étape 2 : exécution du jar avec wait-for-it
FROM openjdk:17
WORKDIR /app

# Ajout du script d'attente
COPY wait-for-it.sh /wait-for-it.sh
RUN chmod +x /wait-for-it.sh

# Ajout du jar compilé depuis l'étape précédente
COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["/wait-for-it.sh", "postgres:5432", "--", "java", "-jar", "app.jar"]
