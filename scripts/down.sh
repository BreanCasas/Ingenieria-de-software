#!/usr/bin/env bash
#
# down.sh
# Detiene y elimina los contenedores del entorno IoTEste (mantiene los
# volúmenes de datos de Mosquitto).
#
set -euo pipefail

SCRIPT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"
COMPOSE_FILE="${SCRIPT_DIR}/../docker/docker-compose.yml"

echo "Bajando entorno IoTEste..."
docker compose -f "${COMPOSE_FILE}" down

echo "Entorno eliminado (volúmenes conservados)."