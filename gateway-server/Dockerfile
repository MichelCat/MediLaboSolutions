FROM eclipse-temurin:21-jre-alpine

WORKDIR /app

COPY ./target/gateway-server-0.0.1-SNAPSHOT.jar ./medilabo-gateway-server.jar
RUN echo "Jar copied"

EXPOSE 9004

CMD [ "java", "-jar", "medilabo-gateway-server.jar" ]