FROM openjdk:17.0-slim
EXPOSE 8080
ARG JAR_FILE=build/libs/*.jar
ARG PROFILE=preview
ENV PROFILE=${PROFILE}
COPY ${JAR_FILE} ./app.jar
ENTRYPOINT java -jar -Dspring.profiles.active=$(echo $PROFILE) /app.jar
