#!/usr/bin/env bash
#
# build.sh
# Compila todos los módulos Java del sistema (subscriber y generator)
# utilizando Docker, sin depender de tener Java o Maven instalados
# en el sistema anfitrión.
#
# Construye las imágenes Docker de cada módulo (lo cual, como efecto
# colateral, compila el código con Maven dentro del propio contenedor
# de build) sin levantar ningún servicio.
#
set -euo pipefail

SCRIPT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"
ROOT_DIR="$(cd "${SCRIPT_DIR}/.." && pwd)"

echo "=== Build de IoTEste EcoWarm (vía Docker) ==="

echo ""
echo "--- Compilando módulo: subscriber ---"
docker build -t ioteste-subscriber:build "${ROOT_DIR}/src/subscriber"

echo ""
echo "--- Compilando módulo: generator ---"
docker build -t ioteste-generator:build "${ROOT_DIR}/src/generator"

echo ""
echo "=== Build completo. Ambos módulos compilaron correctamente. ==="