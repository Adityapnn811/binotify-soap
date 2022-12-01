FROM maven:3.8.6-jdk-8-slim
WORKDIR /app
COPY /webservice .

CMD ["java", "-jar", "/app/target/webservice-1.0-SNAPSHOT-jar-with-dependencies.jar"]
EXPOSE 4040