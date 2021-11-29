include ../common.mk

############################################################################
# Variables
############################################################################

# IMAGE defines docker image
IMAGE ?= test_app
# TAG defines docker tag
TAG ?= latest

# DOCKER_CMD stores docker command
DOCKER_CMD := $(shell command -v docker)

# Run all by default when "make" is invoked.
.DEFAULT_GOAL := help

############################################################################
# Targets
############################################################################

# Run information command with target descriptions.
.PHONY: help
##= docker-build: to build docker image
##= docker-run: to run docker image
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

# Run docker run command.
.PHONY: docker-run
docker-run:
	$(DOCKER_CMD) run -p 15000:5000 -d "$(IMAGE):$(TAG)"
