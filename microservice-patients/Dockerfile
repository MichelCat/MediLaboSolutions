FROM eclipse-temurin:21-jre-alpine

WORKDIR /app

COPY ./target/microservice-patients-0.0.1-SNAPSHOT.jar ./medilabo-patients.jar
RUN echo "Jar copied"

EXPOSE 9001

CMD [ "java", "-jar", "medilabo-patients.jar" ]
