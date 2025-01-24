# Utiliser l'image Java 17 Alpine officielle
FROM openjdk:17-alpine

# Copier le fichier JAR de l'application dans le conteneur
ADD /target/foyer.jar foyer.jar

# Exposer le port sur lequel l'application écoute (ex: 8089)
EXPOSE 8089

# Commande pour exécuter l'application
CMD ["java", "-jar", "foyer.jar"]
