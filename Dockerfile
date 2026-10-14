# --- ЭТАП 1: "СБОРКА" (Build Stage) ---
FROM maven:3.9-eclipse-temurin-21 AS builder

# Устанавливаем рабочую директорию
WORKDIR /app

# Копируем pom.xml
COPY pom.xml .

# Копируем исходный код
COPY src ./src

# Собираем приложение
RUN mvn package -DskipTests

# --- ЭТАП 2: "ЗАПУСК" (Run Stage) ---
FROM eclipse-temurin:21-jre-jammy

USER root
RUN apt-get update && apt-get install -y curl && apt-get clean && rm -rf /var/lib/apt/lists/*

WORKDIR /app

COPY --from=builder /app/target/agora-stats-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]