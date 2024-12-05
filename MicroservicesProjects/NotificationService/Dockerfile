FROM openjdk:17-jdk-slim
WORKDIR /app
COPY target/NotificationService-0.0.1-SNAPSHOT.jar app.jar
COPY dockerize /usr/local/bin/dockerize
RUN chmod +x /usr/local/bin/dockerize
EXPOSE 8081
# Komenda startowa: czekaj na dostępność bazy danych na porcie 3306 przed uruchomieniem aplikacji
ENTRYPOINT ["dockerize", "-wait", "tcp://weather-db:3306", "-timeout", "60s", "java", "-jar", "app.jar"]
