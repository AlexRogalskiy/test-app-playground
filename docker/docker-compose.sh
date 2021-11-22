#!/usr/bin/env bash

## setup base directory
BASE_DIR=$(dirname "$0")

## common functions setup
source "$BASE_DIR/commons.sh"

usage() {
  read -r -d '' MESSAGE <<- EOM
    Usage: ${0} <operation>

    where operation is one of the following:
        up: to start docker containers
        down: to stop docker containers
        logs: to view docker containers logs
EOM
  _newline
  _log_info "$MESSAGE"
  _newline
  _exit 1
}

execute() {
  ## setup directory with docker configurations
  cd "$BASE_DIR/docker-compose" || _exit 1 "Unable to change working directory"
  _run -f docker-compose-camunda.yaml \
       -f docker-compose-hasura.yaml \
       "$@"
}

start() {
  ## setup environment variables
  cp -n "$BASE_DIR/env.example" "$BASE_DIR/docker-compose/.env" && _log_success "Setup env variables"
  execute up -d --build --force-recreate "$@"
}

stop() {
  execute down --volumes --remove-orphans "$@"
}

logs() {
  execute logs -f -t "$@"
}

cleanup() {
  _log_message "Starting cleanup"
  stop
}

trap cleanup SIGTERM SIGINT ERR

main() {
  [ ${#@} -gt 0 ] || usage

  case "$1" in
  up)
    start "${@:2}"
    ;;
  down)
    stop "${@:2}"
    ;;
  logs)
    logs "${@:2}"
    ;;
  *)
    usage
    ;;
  esac
}

main "$@"
