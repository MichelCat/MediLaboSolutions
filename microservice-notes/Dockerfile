FROM eclipse-temurin:21-jre-alpine

WORKDIR /app

COPY ./target/microservice-notes-0.0.1-SNAPSHOT.jar ./medilabo-notes.jar
RUN echo "Jar copied"

EXPOSE 9005

CMD [ "java", "-jar", "medilabo-notes.jar" ]