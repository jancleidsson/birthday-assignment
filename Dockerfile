FROM adoptopenjdk/openjdk11:x86_64-debian-jdk-11.0.5_10-slim as BUILD_IMAGE

USER root

ENV APP_HOME /app

RUN mkdir $APP_HOME

WORKDIR $APP_HOME

COPY build.gradle.kts \
  gradlew \
  settings.gradle.kts \
  $APP_HOME/
COPY gradle $APP_HOME/gradle
COPY src $APP_HOME/src

RUN $APP_HOME/gradlew -q clean build fatJar -x test

RUN mv $APP_HOME/build/libs/birthday-assignment-1.0.jar $APP_HOME/birthday-assignment-1.0.jar