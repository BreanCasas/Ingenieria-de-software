#!/usr/bin/env bash
#
# receive-temp.sh
# Se suscribe al broker MQTT local y muestra por consola todos los
# mensajes publicados bajo el topic "shellies/#". Sirve como
# verificación manual, independiente del suscriptor Java.
#
# Uso:
#   ./receive-temp.sh
#
set -euo pipefail

BROKER_HOST="${MQTT_BROKER_HOST:-localhost}"
BROKER_PORT="${MQTT_BROKER_PORT:-1883}"
TOPIC="shellies/#"

echo "Suscrito a ${BROKER_HOST}:${BROKER_PORT} topic=${TOPIC}"
echo "Esperando mensajes... (Ctrl+C para salir)"
echo

mosquitto_sub -h "${BROKER_HOST}" -p "${BROKER_PORT}" -t "${TOPIC}" -v