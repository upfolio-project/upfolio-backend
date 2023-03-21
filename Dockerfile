FROM eclipse-temurin:18.0.2.1_1-jre-jammy
ARG JAR_FILE=/target/*.jar
COPY ${JAR_FILE} application.jar
ENTRYPOINT ["java", "-jar", "application.jar"]

