FROM openjdk:11-jre-slim
COPY target/library-0.0.1-SNAPSHOT.jar /library.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/library.jar"]