FROM openjdk

EXPOSE 8080

ADD target/BackendCloudService-0.0.1-SNAPSHOT.jar BackendCloudService.jar

CMD ["java", "-jar", "BackendCloudService.jar"]