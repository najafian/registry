FROM maven:3.6.3-jdk-11-slim
#COPY entrypoint.sh /usr/local/bin/entrypoint.sh
#RUN apt-get update && apt-get install dos2unix && dos2unix /usr/local/bin/entrypoint.sh && chmod +x /usr/local/bin/entrypoint.sh
ARG REGISTRY_DOCKER_PORT
ENV REGISTRY_DOCKER_PORT=$REGISTRY_DOCKER_PORT
ENV APP_HOME=/usr/
#Start application
WORKDIR $APP_HOME

COPY src/main/resources/application.yml application.yml
COPY src/main/resources/bootstrap.yml bootstrap.yml
COPY target/*.jar app.jar
CMD ["java","-jar","app.jar"]