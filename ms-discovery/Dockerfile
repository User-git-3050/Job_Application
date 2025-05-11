FROM openjdk:17-jdk-alpine

COPY build/libs/ms-discovery.jar  /app/ms-discovery.jar

EXPOSE 8761

CMD ["java", "-jar", "/app/ms-discovery.jar"]