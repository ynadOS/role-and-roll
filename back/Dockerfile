FROM maven:3.9-eclipse-temurin-21 AS build
WORKDIR /app

# Étape 1 : récupérer uniquement pom.xml pour installer les dépendances
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Étape 2 : copier le reste du code et compiler
COPY src ./src
RUN mvn clean package -DskipTests -Djib.skip

FROM eclipse-temurin:21
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
CMD ["java", "-jar", "app.jar"]