# --- Etapa de build ---
FROM maven:3.9-eclipse-temurin-25 AS build
WORKDIR /build
COPY pom.xml .
COPY src ./src
RUN mvn -q clean package -DskipTests

# --- Etapa de ejecución ---
FROM eclipse-temurin:25-jre-alpine
WORKDIR /app
COPY --from=build /build/target/subscriber-jar-with-dependencies.jar ./subscriber.jar

ENTRYPOINT ["java", "-jar", "subscriber.jar"]