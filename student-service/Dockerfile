FROM openjdk:8-jdk-alpine

COPY ./target/student-service-0.0.1.jar /usr/app/

WORKDIR /usr/app/

RUN sh -c 'touch student-service-0.0.1.jar'

ENTRYPOINT ["java","-jar","student-service-0.0.1.jar"]