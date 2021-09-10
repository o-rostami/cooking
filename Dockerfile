FROM maven:3.6.3-adoptopenjdk-8 AS build
COPY pom.xml /
COPY src /src
RUN mvn -f /pom.xml clean package -DskipTests
FROM openjdk:8-jdk-alpine
ENV JAVA_OPTS ""
COPY --from=build /target/coocking-app.jar /usr/app/cooking-app.jar
EXPOSE 8080
CMD exec java $JAVA_OPTS -jar /usr/app/cooking-app.jar $0 $@
