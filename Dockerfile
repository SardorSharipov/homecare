FROM eclipse-temurin:22-jdk

WORKDIR /app

COPY src src

COPY target/homecare-0.0.1-SNAPSHOT.jar homecare.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "homecare.jar"]