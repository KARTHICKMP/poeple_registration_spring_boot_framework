FROM openjdk:8

ADD target/people-registartion-application.jar people-registartion-application.jar

EXPOSE 8081

ENTRYPOINT ["java", "-jar", "people-registartion-application.jar"]
