FROM gradle:8.7-jdk21-jammy AS builder
WORKDIR /app
COPY build.gradle settings.gradle ./
COPY gradlew .
COPY gradle gradle
COPY src src
RUN chmod +x ./gradlew
RUN ./gradlew bootJar --no-daemon

FROM openjdk:21-jdk-slim
ARG JAR_FILE=/app/build/libs/*.jar
COPY --from=builder ${JAR_FILE} app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app.jar"]