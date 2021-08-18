FROM openjdk:8

ADD /var/lib/jenkins/workspace/PeopleRegistration_Project_Deployment/target/people-registartion-application people-registartion-application.jar

EXPOSE 8081

ENTRYPOINT ["java", "-jar", "people-registartion-application.jar"]
