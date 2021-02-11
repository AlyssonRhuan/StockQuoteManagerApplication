FROM openjdk:8-jdk-alpine
ENV SPRING_DATASOURCE_URL=jdbc:mysql://docker-mysql:3306/bootdb?useSSL=false&allowPublicKeyRetrieval=true&createDatabaseIfNotExist=true
ENV APP_URL=http://app:8080/
COPY target/stock-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]