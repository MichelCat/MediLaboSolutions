FROM eclipse-temurin:21-jre-alpine

WORKDIR /app

COPY ./target/eureka-server-0.0.1-SNAPSHOT.jar ./medilabo-eureka-server.jar
RUN echo "Jar copied"

EXPOSE 9102

CMD [ "java", "-jar", "medilabo-eureka-server.jar" ]