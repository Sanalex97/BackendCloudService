FROM openjdk

EXPOSE 8080

COPY target/BackendCloudService-0.0.1-SNAPSHOT.jar BackendCloudService.jar

ENV TZ=Europe/Moscow

CMD ["java", "-jar", "BackendCloudService.jar"]