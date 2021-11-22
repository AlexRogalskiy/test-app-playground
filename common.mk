############################################################################
# General
############################################################################

# SHELLFLAGS stores the shell flags.
.SHELLFLAGS := -o errexit -o nounset
MAKEFLAGS += --no-builtin-rules --no-print-directory

############################################################################
# Variables
############################################################################

# SHELL stores the shell that the Makefile uses.
SHELL := /bin/bash
# ROOT_DIR stores git root directory
ROOT_DIR := $(shell git rev-parse --show-toplevel)
# UNAME_ARCH stores the value of uname -m.
UNAME_ARCH := $(shell uname -m | sed s/x86_64/amd64/ | sed s/aarch64/arm64/ | sed s/aarch64_be/arm64/)
# GIT_SHA stores git last commit hash
GIT_SHA := $(shell git rev-parse --verify HEAD)

# DOCKER_CLI_EXPERIMENTAL stores docker cli experimental option
DOCKER_CLI_EXPERIMENTAL ?= enabled
# DOCKER_BUILDKIT stores docker BuildKit option
DOCKER_BUILDKIT ?= 1
# COMPOSE_DOCKER_CLI_BUILD stores docker cli build option
COMPOSE_DOCKER_CLI_BUILD ?= 1

# Set V=1 on the command line to turn off all suppression. Many trivial
# commands are suppressed with "@", by setting V=1, this will be turned off.
ifeq ($(V),1)
	AT :=
else
	AT := @
endif

ifeq ($(UTILS),)
  override UTILS := docker docker-compose
endif

# Make sure that all required utilities can be located.
UTIL_CHECK := $(or $(shell which $(UTILS) >/dev/null && echo 'ok'),$(error Did you forget to install [$(UTILS)] after cloning the repo? At least one of the required supporting utilities not found: $(UTILS)))

# Since we rely on paths relative to the makefile location, abort if make isn't being run from there.
$(if $(findstring /,$(MAKEFILE_LIST)),$(error Please only invoke this makefile from the directory it resides in))

############################################################################
# Common targets
############################################################################

# Ensure build version command.
.PHONY: _ensure-version
_ensure-version:
ifndef BUILD_VERSION
	$(error Please invoke with `make BUILD_VERSION=<version>`)
endif
