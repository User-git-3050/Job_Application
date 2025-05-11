FROM openjdk:17-jdk-alpine

COPY build/libs/ms-gateway.jar  /app/ms-gateway.jar

EXPOSE 8080

CMD ["java", "-jar", "/app/ms-gateway.jar"]