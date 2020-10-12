FROM openjdk:8-jdk-alpine

RUN addgroup -S malesela && adduser -S malesela -G malesela

USER malesela:malesela

ARG JAR_FILE=target/*.jar

COPY ${JAR_FILE} app.jar

ENTRYPOINT ["java", "-jar", "/app.jar"]
