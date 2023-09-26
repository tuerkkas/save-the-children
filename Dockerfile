FROM openjdk:17
ARG JAR_FILE=TARGET/*.jar
COPY ./target/users-0.0.1-SNAPSHOT.jar users.jar
ENTRYPOINT ["java","-jar","/users.jar"]