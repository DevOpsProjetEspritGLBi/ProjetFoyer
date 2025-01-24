# Utiliser l'image complète d'OpenJDK 17
FROM openjdk:17

# Définir le répertoire de travail
WORKDIR /app

# Exposer le port utilisé par l'application
EXPOSE 8082

# Copier le fichier JAR dans l'image Docker
COPY target/tpFoyer-17-0.0.1-SNAPSHOT.jar app.jar

# Exécuter l'application
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
