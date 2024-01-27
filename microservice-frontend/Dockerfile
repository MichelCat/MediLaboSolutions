FROM eclipse-temurin:21-jre-alpine

WORKDIR /app

COPY ./target/microservice-frontend-0.0.1-SNAPSHOT.jar ./medilabo-frontend.jar
RUN echo "Jar copied"

EXPOSE 8080

CMD [ "java", "-jar", "medilabo-frontend.jar" ]