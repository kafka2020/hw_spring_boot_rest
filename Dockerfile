FROM eclipse-temurin:25-jre-alpine
WORKDIR /app
ADD target/hw_spring_boot_rest-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]