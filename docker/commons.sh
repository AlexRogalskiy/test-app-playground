#!/usr/bin/env bash

# enable/disable shell options
TRUE_REG='^([tT][rR][uU][eE]|[yY]|[yY][eE][sS]|1)$'

# start message prefix
START_GROUP=${START_GROUP:-START}
# end message prefix
END_GROUP=${END_GROUP:-END}

# console color points
if command -v tput &>/dev/null && tty -s; then
  COLOR_RED=$(tput setaf 1)
  COLOR_GREEN=$(tput setaf 2)
  COLOR_YELLOW=$(tput setaf 3)
  COLOR_BOLD=$(tput bold)
  COLOR_END=$(tput sgr0)
else
  COLOR_RED=$(echo -e "\e[31m")
  COLOR_GREEN=$(echo -e "\e[32m")
  COLOR_YELLOW=$(echo -e "\e[33m")
  COLOR_BOLD=$(echo -e "\e[01m")
  COLOR_END=$(echo -e "\e[00m")
fi

# enable/disable debug output
DEBUG_SCRIPT=${DEBUG_SCRIPT:-false}
if [[ $DEBUG_SCRIPT =~ $TRUE_REG ]]; then
  set -o xtrace
fi

# enable/disable strict mode
STRICT_SCRIPT=${STRICT_SCRIPT:-false}
if [[ $STRICT_SCRIPT =~ $TRUE_REG ]]; then
  set -o errexit
  set -o nounset
  set -o pipefail
fi

# enable/disable docker BuildKit option
export DOCKER_BUILDKIT=${DOCKER_BUILDKIT:-1}
# enable/disable docker cli build option
export COMPOSE_DOCKER_CLI_BUILD=${COMPOSE_DOCKER_CLI_BUILD:-1}
# DOCKER_COMPOSE_CMD stores docker-compose command
DOCKER_COMPOSE_CMD=$(command -v docker-compose || command -v docker compose)

_newline() {
  printf "\n"
}

_success() {
  printf "${COLOR_GREEN} ✔ [ %s ]${COLOR_END}" "$@" >&2
  _newline
}

_error() {
  printf "${COLOR_RED} ✖ [ %s ]${COLOR_END}" "$@" >&2
  _newline
}

_info() {
  printf "${COLOR_YELLOW} %s ${COLOR_END}" "$@" >&2
  _newline
}

_message() {
  _newline
  printf "${COLOR_BOLD}${COLOR_YELLOW}==========  %s  ==========${COLOR_END}" "$@" >&2
  _newline
  _newline
}

_log() {
  (($# > 0)) && echo "$@" >&2
}

_exit() {
  (($# > 1)) && _error "${@:2}"
  exit "$1"
}

_log_success() {
    (($# > 0)) && _success "$@"
}

_log_error() {
    (($# > 0)) && _error "$@"
}

_log_info() {
    (($# > 0)) && _info "$@"
}

_log_message() {
    (($# > 0)) && _message "$@"
}

_log_start_group() {
    _log_message "${START_GROUP}"
}

_log_end_group() {
    _log_message "${END_GROUP}"
}

_docker_compose() {
  $DOCKER_COMPOSE_CMD "$@"
}

_run() {
  _log_start_group
  _log_success "Running script $(basename "$0")"

  _docker_compose "$@"

  _log_success "Done!"
  _log_end_group
}
