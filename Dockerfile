FROM openjdk:17-jdk-slim
COPY build/libs/\*.jar batch-0.0.3-SNAPSHOT.jar
CMD ["java", "-jar", "batch-0.0.3-SNAPSHOT.jar"]