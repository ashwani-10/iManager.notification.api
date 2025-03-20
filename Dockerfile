FROM openjdk:21-jdk
WORKDIR /app
COPY target/iManagerNotification-0.0.1-SNAPSHOT.jar iManagerNotification.jar
CMD ["java", "-jar", "iManagerNotification.jar"]