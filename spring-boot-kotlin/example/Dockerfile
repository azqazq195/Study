FROM openjdk:17.0-slim
EXPOSE 80
ARG JAR_FILE=build/libs/*.jar
ARG PROFILE=development
ENV PROFILE=${PROFILE}
COPY ${JAR_FILE} ./app.jar
ENTRYPOINT java -jar -Dspring.profiles.active=$(echo $PROFILE) /app.jar
