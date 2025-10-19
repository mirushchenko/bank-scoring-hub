FROM openjdk:17-jdk-slim

WORKDIR /app

# Копируем JAR файл
COPY target/client-info-service-1.0.0.jar app.jar

# Открываем порты
EXPOSE 9090

# Запускаем приложение
ENTRYPOINT ["java", "-jar", "app.jar"]
