FROM openjdk:17-jdk-slim

WORKDIR /app

COPY target/scoring-service-1.0.0.jar app.jar

EXPOSE 9091

ENTRYPOINT ["java", "-jar", "app.jar"]
