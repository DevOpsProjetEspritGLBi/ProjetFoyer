# Étape 1 : Utiliser l'image complète d'OpenJDK 17
FROM openjdk:17

# Étape 2 : Exposer le port sur lequel l'application Spring Boot s'exécute
EXPOSE 8082

# Étape 3 : Ajouter le fichier JAR généré dans l'image Docker
ADD target/tpFoyer-17-0.0.1-SNAPSHOT.jar tpFoyer-17-0.0.1-SNAPSHOT.jar

# Étape 4 : Définir le point d'entrée du conteneur pour exécuter l'application
ENTRYPOINT ["java", "-jar", "/tpFoyer-17-0.0.1-SNAPSHOT.jar"]
