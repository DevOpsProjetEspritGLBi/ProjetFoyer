# Étape 1 : Utiliser une image Maven pour la phase de build
FROM maven:3.8.5-openjdk-17 AS build

# Définir le répertoire de travail dans le conteneur
WORKDIR /app

# Copier les fichiers source dans le conteneur
COPY . .

# Construire le projet avec Maven (sans les tests pour accélérer le build)
RUN mvn clean package -DskipTests

# Étape 2 : Utiliser une image minimale pour exécuter l'application
FROM openjdk:17-jdk-slim

# Définir le répertoire de travail pour l'application
WORKDIR /app

# Copier uniquement le fichier JAR construit depuis l'étape précédente
COPY --from=build /app/target/*.jar app.jar

# Exposer le port sur lequel l'application tourne (par exemple : 8080)
EXPOSE 8080

# Commande pour exécuter l'application
ENTRYPOINT ["java", "-jar", "app.jar"]
