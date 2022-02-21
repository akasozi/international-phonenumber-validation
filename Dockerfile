FROM openjdk:8-jdk-alpine
ADD target/international-phonenumber-validation-0.0.1-SNAPSHOT.jar international-phonenumber-validation-0.0.1-SNAPSHOT.jar
COPY sample.db sample.db
ENTRYPOINT ["java","-jar","/international-phonenumber-validation-0.0.1-SNAPSHOT.jar"]
EXPOSE 8081