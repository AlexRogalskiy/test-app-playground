include common.mk

############################################################################
# Variables
############################################################################

# DOCKER_COMPOSE_CMD stores docker-compose command
DOCKER_COMPOSE_CMD := $(shell command -v docker-compose || command -v docker compose)

# Run all by default when "make" is invoked.
.DEFAULT_GOAL := help

############################################################################
# Targets
############################################################################

# Run information command with target descriptions.
.PHONY: help
##= docker-up: to run docker container
##= docker-down: to stop docker container
##= docker-log: to view docker container logs
help:
	$(AT)echo
	$(AT)echo
	$(AT)echo "Please use [make <target>] where <target> is one of:"
	$(AT)echo
	$(AT)sed -n 's/^##=//p' $(MAKEFILE_LIST) 2>/dev/null | column -t -s ':' |  sed -e 's/^/ /'
	$(AT)echo
	$(AT)echo

# Run docker start command.
.PHONY: docker-up
docker-up:
	$(DOCKER_COMPOSE_CMD) -f $(ROOT_DIR)/docker/docker-compose.yaml up -d --build --force-recreate

# Run docker stop command.
.PHONY: docker-down
docker-down:
	$(DOCKER_COMPOSE_CMD) -f $(ROOT_DIR)/docker/docker-compose.yaml down --remove-orphans --volumes

# Run docker logs command.
.PHONY: docker-logs
docker-logs:
	$(DOCKER_COMPOSE_CMD) -f $(ROOT_DIR)/docker/docker-compose.yaml logs -t -f
