include ../common.mk

############################################################################
# Variables
############################################################################

# IMAGE defines docker image
IMAGE ?= test-app-playground
# TAG defines docker tag
TAG ?= latest

# MVN_CMD stores maven package manager
MVN_CMD := $(shell command -v ./mvnw || command -v mvn)
# JAVA_CMD stores java executable command
JAVA_CMD := $(shell command -v java)
# DOCKER_CMD stores docker command
DOCKER_CMD := $(shell command -v docker)
# MAVEN_PROFILES stores default maven profiles
MAVEN_PROFILES := "dev"
# MAVEN_ARGS stores default maven arguments
MAVEN_ARGS := --file pom.xml --show-version --batch-mode --errors --lax-checksums -Dinvoker.skip=true -Dmaven.exec.skip=true -Dmaven.install.skip -Dmaven.resources.skip -P$(MAVEN_PROFILES)

# Run all by default when "make" is invoked.
.DEFAULT_GOAL := help

############################################################################
# Targets
############################################################################

# Run information command with target descriptions.
.PHONY: help
##= build: to build project dependencies
##= docker-build: to build docker image
##= format: to format java files
##= lint: to lint java files
##= local-chrome-run: to run test suites locally with chrome browser
##= local-firefox-run: to run test suites locally with firefox browser
##= test: to run project tests
##= update: to update project dependencies
##= version: to create new release version
help:
	$(AT)echo
	$(AT)echo
	$(AT)echo "Please use [make <target>] where <target> is one of:"
	$(AT)echo
	$(AT)sed -n 's/^##=//p' $(MAKEFILE_LIST) 2>/dev/null | column -t -s ':' |  sed -e 's/^/ /'
	$(AT)echo
	$(AT)echo

# Run docker build command.
.PHONY: docker-build
docker-build:
	$(DOCKER_CMD) build --rm --platform=linux/$(UNAME_ARCH) -f Dockerfile -t "$(IMAGE):$(TAG)" -t "$(IMAGE):$(GIT_SHA)" .

# Run maven build command.
.PHONY: build
build:
	$(MVN_CMD) clean package $(MAVEN_ARGS) -Puber

# Run maven test command.
.PHONY: test
test:
	$(MVN_CMD) clean test $(MAVEN_ARGS) -Pit

# Run maven offline command.
.PHONY: offline
offline:
	$(MVN_CMD) clean verify $(MAVEN_ARGS) --fail-never dependency:copy-dependencies dependency:resolve-plugins dependency:go-offline

# Run test suites locally with chrome browser.
.PHONY: local-chrome-run
local-chrome-run:
	$(JAVA_CMD) -Dselenide.browser=chrome -Dselenide.baseurl=http://localhost:15000 -jar .build/bin/tech.arenadata.api.test.it/tech.arenadata.api.test-test-api-it-1.0.0-uber.jar

# Run test suites locally with firefox browser.
.PHONY: local-firefox-run
local-firefox-run:
	$(JAVA_CMD) -Dselenide.browser=firefox -Dselenide.baseurl=http://localhost:15000 -jar .build/bin/tech.arenadata.api.test.it/tech.arenadata.api.test-test-api-it-1.0.0-uber.jar

# Run maven update dependencies command.
.PHONY: update
update:
	$(MVN_CMD) versions:display-dependency-updates
	$(MVN_CMD) versions:display-plugin-updates

# Run maven new version build.
.PHONY: version
version: _ensure-version
	$(MVN_CMD) versions:set \
		-DnewVersion=$(BUILD_VERSION)
		-Dmessage="prepare release $(BUILD_VERSION)"
		-DpushChanges=false -DgenerateBackupPoms=false -B 1>/dev/null 2>/dev/null

# Run maven spotless format command.
.PHONY: format
format:
	$(MVN_CMD) spotless:apply $(MAVEN_ARGS) -Pcheckstyle

# Run maven spotless check command.
.PHONY: lint
lint:
	$(MVN_CMD) spotless:check $(MAVEN_ARGS) -Pcheckstyle
