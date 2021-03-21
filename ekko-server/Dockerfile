FROM gradle:jdk11-openj9 AS GRADLE_BUILD
COPY ekko-server /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle build --no-daemon 

FROM openjdk:11.0.10-slim

#EXPOSE 8080

RUN mkdir /app

COPY --from=GRADLE_BUILD /home/gradle/src/build/libs/ekko-server-0.0.1-SNAPSHOT.jar /app/

ENTRYPOINT ["java", "-jar", "/app/ekko-server-0.0.1-SNAPSHOT.jar"]