FROM gradle:4.7.0-jdk8-alpine AS build
COPY --chown=gradle:gradle . /app

WORKDIR /app


RUN ./gradlew clean build --no-daemon

FROM openjdk:8-jre-alpine
WORKDIR /app
COPY --chmod=0755 --from=build /app/trippin/build/libs/trippin-0.0.1-SNAPSHOT.jar /app
EXPOSE 8080
ENTRYPOINT ["sh", "-c"]
CMD ["java -jar trippin-0.0.1-SNAPSHOT.jar"]