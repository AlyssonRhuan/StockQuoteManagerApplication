FROM openjdk:11
COPY target/stock-0.0.1-SNAPSHOT.jar ./app.jar
ENTRYPOINT ["java","-jar","/app.jar"]