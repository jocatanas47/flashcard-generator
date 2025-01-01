FROM openjdk:21-slim

WORKDIR /app

COPY . .

RUN ./mvnw clean package

ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar

EXPOSE 8080

CMD ["java", "-jar", "/app.jar"]
