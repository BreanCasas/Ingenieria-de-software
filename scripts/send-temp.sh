#!/usr/bin/env bash
#
# send-temp.sh
# Publica un mensaje simulado de un sensor Shelly H&T (temperatura/humedad)
# en el broker MQTT local, en formato JSON.
#
# Uso:
#   ./send-temp.sh                # valores por defecto
#   ./send-temp.sh 23.5 55        # temperatura=23.5 humedad=55
#
set -euo pipefail

BROKER_HOST="${MQTT_BROKER_HOST:-localhost}"
BROKER_PORT="${MQTT_BROKER_PORT:-1883}"
TOPIC="shellies/shellyht-test/status"

TEMP="${1:-22.0}"
HUM="${2:-50}"

PAYLOAD=$(cat <<PAYLOAD_EOF
{"temperature": ${TEMP}, "humidity": ${HUM}, "battery": 88}
PAYLOAD_EOF
)

echo "Publicando en ${BROKER_HOST}:${BROKER_PORT} topic=${TOPIC}"
echo "Payload: ${PAYLOAD}"

mosquitto_pub -h "${BROKER_HOST}" -p "${BROKER_PORT}" -t "${TOPIC}" -m "${PAYLOAD}"

echo "Mensaje publicado."