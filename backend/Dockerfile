FROM maven:3.8.1-jdk-11-slim AS build
COPY pom.xml /build/
COPY src /build/src/
WORKDIR /build/
RUN mvn -DskipTests=true package

FROM openjdk:11-jdk-slim
WORKDIR /app
COPY --from=build /build/target/idea-0.0.1-SNAPSHOT.jar /app/
EXPOSE 8080
ENTRYPOINT ["java","-jar","idea-0.0.1-SNAPSHOT.jar"]