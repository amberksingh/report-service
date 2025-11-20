#================================
#OLD WAY without creating jar using Docker
#FROM eclipse-temurin:17-jdk
#WORKDIR /report-service
#COPY target/report-service-1.0-SNAPSHOT.jar report-service.jar
#ENTRYPOINT ["java", "-jar", "report-service.jar"]
#================================

# ================================
# 1️⃣ BUILD STAGE - MAVEN COMPILATION
# ================================
FROM maven:3.9.6-eclipse-temurin-17 AS builder
WORKDIR /app

# Copy pom.xml first to enable dependency caching
COPY pom.xml .
RUN mvn -e -B dependency:go-offline

# Copy source code
COPY src ./src

# Package the application
RUN mvn -e -B clean package -DskipTests

# ================================
# 2️⃣ RUN STAGE - LIGHTWEIGHT JDK
# ================================
FROM eclipse-temurin:17-jdk
WORKDIR /app

# Copy only the built JAR from builder image
COPY --from=builder /app/target/*.jar report-service.jar

# Run the application
ENTRYPOINT ["java", "-jar", "report-service.jar"]

#During build (using builder stage):
#/app
#   ├── pom.xml
#   ├── src/
#   ├── target/
#   │     └── report-service.jar
