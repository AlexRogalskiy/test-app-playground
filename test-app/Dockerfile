FROM maven:3.8.4-openjdk-11-slim AS build

## setup image arguments
ARG APP_DIR="/home/app"

## setup working directory
WORKDIR "$APP_DIR"

## create project structure
RUN mkdir -p ./modules/assertions \
    && mkdir -p ./modules/bom \
    && mkdir -p ./modules/client \
    && mkdir -p ./modules/commons \
    && mkdir -p ./modules/extension \
    && mkdir -p ./modules/it

## copy source files
COPY pom.xml ./
COPY modules/assertions/pom.xml  ./modules/assertions/
COPY modules/bom/pom.xml  ./modules/bom/
COPY modules/client/pom.xml  ./modules/client/
COPY modules/commons/pom.xml  ./modules/commons/
COPY modules/extensions/pom.xml  ./modules/extensions/
COPY modules/it/pom.xml  ./modules/it/

## build dependencies
RUN --mount=type=cache,target=/root/.m2 mvn -f pom.xml clean verify --fail-never dependency:copy-dependencies dependency:resolve-plugins dependency:go-offline

## copy source files
COPY modules/assertions/src  ./modules/assertions/src/
COPY modules/client/src  ./modules/client/src/
COPY modules/commons/src  ./modules/commons/src/
COPY modules/extensions/src  ./modules/extensions/src/
COPY modules/it/src  ./modules/it/src/

## build project
RUN --mount=type=cache,target=/root/.m2 mvn -f pom.xml clean package

FROM openjdk:11-slim

## setup image arguments
ARG IMAGE_NAME="test-app-playground"
ARG IMAGE_DESCRIPTION="Arenadata Test Application Playground"
ARG IMAGE_URL="https://github.com/AlexRogalskiy/test-app-playground"
ARG IMAGE_REVISION="$(git rev-parse HEAD)"
ARG IMAGE_VERSION="$(git describe --abbrev=0 --tag)"
ARG IMAGE_BUILD_DATE="$(date -u +\"%Y-%m-%dT%H:%M:%SZ\")"

ARG JAR_FILE="*uber.jar"
ARG APP_DIR="/home/app"
ARG LIB_DIR="/usr/local/lib"
ARG BUILD_DIR=".build/bin/tech.arenadata.api.test.it"

## setup environment variables
ENV APP_DIR="$APP_DIR" \
    LIB_DIR="$LIB_DIR" \
    BUILD_DIR="$BUILD_DIR"

ENV JAVA_OPTS="-Djava.security.egd=file:/dev/./urandom" \
    JAVA_VM_OPTS="-XX:NativeMemoryTracking=summary -Xms32m -Xmx2g "

## setup image labels
LABEL "org.opencontainers.image.title"="$IMAGE_NAME" \
      "org.opencontainers.image.description"="$IMAGE_DESCRIPTION" \
      "org.opencontainers.image.url"="$IMAGE_URL" \
      "org.opencontainers.image.source"="$IMAGE_URL" \
      "org.opencontainers.image.revision"="$IMAGE_REVISION" \
      "org.opencontainers.image.version"="$IMAGE_VERSION" \
      "org.opencontainers.image.created"="$IMAGE_BUILD_DATE"

## copy build files
COPY --from=build --chown=1001 "$APP_DIR/$BUILD_DIR/$JAR_FILE" "$LIB_DIR/app.jar"

## setup working directory
WORKDIR "$LIB_DIR"

## setup entrypoint
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS $JAVA_VM_OPTS -jar app.jar" ]
