FROM openjdk:17-jdk-slim

COPY target/devops-0.0.1-SNAPSHOT.jar Devops.jar

EXPOSE 8085

ENTRYPOINT ["java", "-jar", "Devops.jar"]
