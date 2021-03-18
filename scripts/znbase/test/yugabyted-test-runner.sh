#!/usr/bin/env bash

set -euo pipefail

# -------------------------------------------------------------------------------------------------
# Variables
# -------------------------------------------------------------------------------------------------

readonly absolute_dir_path="$(dirname "$(readlink -f "$0")")"
readonly test_script="${absolute_dir_path}/ZNbased-test.sh"
readonly logfile="/tmp/ZNbased-test-runner-$( date +%Y-%m-%dT%H_%M_%S ).log"

python_interpreter=python
yb_latest_version="$(curl --silent "https://api.github.com/repos/ZNbase/ZNbase-db/tags" \
  | jq '.[].name' | head -1)"
docker_image="ZNbasedb/ZNbase:latest"
testsuite="basic"
ZNbased=

declare -a test_args

if [[ $OSTYPE == linux* ]]; then
  package="https://github.com/ZNbase/ZNbase-db/releases/download/${yb_latest_version//\"/}/ZNbase-${yb_latest_version//[v\"]/}-linux.tar.gz"
fi

if [[ $OSTYPE == darwin* ]]; then
  package="https://github.com/ZNbase/ZNbase-db/releases/download/${yb_latest_version//\"/}/ZNbase-${yb_latest_version//[v\"]/}-darwin.tar.gz"
fi

# -------------------------------------------------------------------------------------------------
# Helper functions
# -------------------------------------------------------------------------------------------------

log() {
  echo >&2 "[$( date +%Y-%m-%dT%H:%M:%S )] $*"
}

fatal() {
  log "$@"
  exit 1
}

print_usage() {
  cat <<-EOT
Usage: ${0##*/} [<options>]
Options:
  -h, --help
    Print usage information.
  -p, --package
    ZNbaseDB package.
  -i, --image
    ZNbaseDB Docker image with tag.
  -y, --ZNbased
    Custom ZNbased.
  -P, --python
    Provide Python interpreter.
  -T, --testsuite
    Provide Test Suite. Default: basic
    [basic|intermediate|advanced]

  Examples:
    1. With defaults.
      ZNbased-test-runner.sh

    2. With Custom ZNbased.
      ZNbased-test-runner.sh -y /path/ZNbased

    3. With Custom ZNbased, docker image and package.
      ZNbased-test-runner.sh -y /path/ZNbased -i image:tag -p /path/package.tar.gz

    4. With different python interpreter.
      ZNbased-test-runner.sh -P python37

    5. With different test suite
      ZNbased-test-runner.sh -T advanced
EOT
}

is_empty() {
  if [[ -z "$1" ]]; then
    fatal "$2 is empty."
  fi
}

is_exist() {
  if  [ ! -f "$1" ]; then
    fatal "Error: Provided $2 doesn't exists."
  fi
}

# -------------------------------------------------------------------------------------------------
# Parsing arguments
# -------------------------------------------------------------------------------------------------

while [[ $# -gt 0 ]]; do
  case "$1" in
    -h|--help)
      print_usage
      exit 0
    ;;
    -p|--package)
      shift
      is_empty "${1-}" "Package"
      if ! [[ "$1" == "https://"* ]]; then
        is_exist "$1" "Package"
      fi
      package=$1
    ;;
    -i|--image)
      shift
      is_empty "${1-}" "Docker image"
      docker_image=$1
    ;;
    -y|--ZNbased)
      shift
      is_empty "${1-}" "ZNbased"
      is_exist "$1" "ZNbased"
      ZNbased=$1
    ;;
    -P|--python)
      shift
      is_empty "${1-}" "Python interpreter"
      if ! $1 --version; then
        fatal "Python Interpreter not found"
      fi
      python_interpreter=$1
    ;;
    -T|--testsuite)
      shift
      is_empty "${1-}" "Test suite"
      if ! [[ "basic intermediate advanced" == *"${1}"* ]]; then
        fatal "Provide valid test suite"
      fi
      testsuite=$1
    ;;
    *)
      print_usage >&2
      echo >&2
      echo "Invalid option: $1" >&2
      exit 1
  esac
  shift
done

# -------------------------------------------------------------------------------------------------
# Main
# -------------------------------------------------------------------------------------------------

test_args+=(
  -p "$package"
  -i "$docker_image"
  -P "$python_interpreter"
  -T "$testsuite"
)

if [[ -n "$ZNbased" ]]; then
  test_args+=(-y "$ZNbased")
fi

log "Test log at ${logfile}"

time "$test_script" "${test_args[@]}" | tee "${logfile}"
