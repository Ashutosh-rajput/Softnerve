FROM openjdk:22-jdk

VOLUME /tmp
EXPOSE 8080

ARG JAR_FILE=target/Softnerve.jar
ADD ${JAR_FILE} app.jar

ENTRYPOINT ["java","-jar","/app.jar"]