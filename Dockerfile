FROM maven:latest as build

WORKDIR /build

COPY . .

RUN mvn clean package -DskipTests

FROM openjdk:21

WORKDIR /app

COPY --from=build ./build/target/*.jar ./foodtech.jar

ENTRYPOINT java -jar foodtech.jar