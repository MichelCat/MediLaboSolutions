FROM eclipse-temurin:21-jre-alpine

WORKDIR /app

COPY ./target/microservice-diabetes-risks-0.0.1-SNAPSHOT.jar ./medilabo-diabetes-risks.jar
RUN echo "Jar copied"

EXPOSE 9006

CMD [ "java", "-jar", "medilabo-diabetes-risks.jar" ]