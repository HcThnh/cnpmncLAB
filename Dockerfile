FROM maven:3.9.4-eclipse-temurin-21 AS build

WORKDIR /app

# Copy pom trước để tận dụng Docker cache
COPY pom.xml .
RUN mvn dependency:go-offline

# Copy source code
COPY src ./src

# Build jar (skip test)
RUN mvn clean package -DskipTests


# =========================
# Stage 2: Run
# =========================
FROM eclipse-temurin:21-jre

WORKDIR /app

# Copy jar từ stage build
COPY --from=build /app/target/*.jar app.jar

# Render yêu cầu dùng PORT env
ENV PORT=8080

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]