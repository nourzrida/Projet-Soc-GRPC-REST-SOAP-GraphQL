# On remplace l'ancienne image openjdk par eclipse-temurin (plus stable)
FROM eclipse-temurin:17-jdk-alpine

VOLUME /tmp

# Copie le JAR
COPY target/*.jar app.jar

ENTRYPOINT ["java","-jar","/app.jar"]