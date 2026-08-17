#!/usr/bin/env bash
#
# stop.sh
# Detiene los contenedores del entorno IoTEste sin eliminarlos
# (a diferencia de down.sh). Útil para pausar el trabajo y retomarlo
# rápido con "docker compose start".
#
set -euo pipefail

SCRIPT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"
COMPOSE_FILE="${SCRIPT_DIR}/../docker/docker-compose.yml"

echo "Deteniendo contenedores de IoTEste..."
docker compose -f "${COMPOSE_FILE}" stop

echo "Contenedores detenidos."