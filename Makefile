include common.mk

############################################################################
# Variables
############################################################################

# Run all by default when "make" is invoked.
.DEFAULT_GOAL := help

############################################################################
# Targets
############################################################################

# Make docker script runnable
.PHONY: _make-runnable
_make-runnable:
	$(AT)chmod +x $(ROOT_DIR)/docker/docker-compose.sh

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
docker-up: _make-runnable
	$(AT)$(ROOT_DIR)/docker/docker-compose.sh up

# Run docker stop command.
.PHONY: docker-down
docker-down: _make-runnable
	$(AT)$(ROOT_DIR)/docker/docker-compose.sh down

# Run docker logs command.
.PHONY: docker-logs
docker-logs: _make-runnable
	$(AT)$(ROOT_DIR)/docker/docker-compose.sh logs
