FROM amazoncorretto:17
COPY build/libs/\*.jar batch-0.0.3-SNAPSHOT.jar
CMD ["java", "-jar", "batch-0.0.3-SNAPSHOT.jar"]