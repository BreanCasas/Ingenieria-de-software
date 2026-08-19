# Ingenieria-de-software
Taller de ingeniria de software , java 25
# IoTEste — Iteración 1

Prototipo base de la plataforma de automatización de oficinas y hogares IoTEste, construido
sobre mensajería MQTT (Eclipse Mosquitto) y un primer componente en Java que consume mensajes
del broker.

## Contexto del proyecto

IoTEste busca posicionarse en el mercado de automatización de oficinas y hogares, integrando
dispositivos IoT (Shelly Pro 1PM y Shelly H&T Gen 3) mediante mensajería MQTT. Esta primera
iteración establece la base técnica: un broker MQTT funcionando y el primer componente Java
capaz de consumir mensajes del sistema.

## Estructura del repositorio

/docs/vision.md Documento de Visión v1 (plantilla de Moore)
/docker/docker-compose.yml Orquestación: broker Mosquitto + suscriptor Java
/docker/mosquitto/config/ Configuración del broker Mosquitto
/scripts/send-temp.sh Publica un mensaje simulado de temperatura/humedad
/scripts/receive-temp.sh Se suscribe por consola (verificación manual)
/scripts/up.sh Levanta el entorno completo (build + up -d)
/scripts/down.sh Baja y elimina los contenedores
/scripts/stop.sh Detiene los contenedores sin eliminarlos
/src/subscriber/ Módulo Java: suscriptor MQTT mínimo (Maven + Eclipse Paho)
/README.md Este archivo

## Requisitos previos

### Linux (Fedora / Ubuntu / etc.)

- Docker y Docker Compose (plugin `docker compose`)
- `mosquitto-clients` instalado localmente, para los scripts de publish/subscribe
  (en Fedora: `sudo dnf install mosquitto` · en Ubuntu/Debian: `sudo apt install mosquitto-clients`)
- (Opcional) [MQTT Explorer](http://mqtt-explorer.com/) o [MQTTX](https://mqttx.app/) para verificación visual cruzada
- **Importante:** si hay un Mosquitto instalado nativamente en el sistema (como servicio),
  detenerlo antes de levantar el entorno Docker, porque ambos compiten por el puerto 1883:
```bash
  sudo systemctl stop mosquitto
  sudo systemctl disable mosquitto
```

### Windows

- **Docker Desktop** (con backend WSL2 habilitado): https://www.docker.com/products/docker-desktop
- Se recomienda trabajar dentro de **WSL2 con Ubuntu** (`wsl --install` desde PowerShell como
  administrador) para poder correr los scripts `.sh` sin modificaciones. Alternativamente,
  **Git Bash** (incluido con Git para Windows) también permite ejecutarlos.
- Cliente Mosquitto para Windows (si no se usa WSL2): https://mosquitto.org/download/
- MQTTX o MQTT Explorer tienen instaladores `.exe` nativos para Windows.

## Cómo levantar el entorno

```bash
cd scripts
./up.sh
```

Esto construye la imagen del suscriptor Java (compilando el código Java con Maven dentro del
contenedor) y levanta dos servicios:

- **ioteste-mosquitto**: broker MQTT, expuesto en `localhost:1883`
- **ioteste-subscriber**: suscriptor Java, conectado automáticamente al broker vía la red
  interna de Docker, suscripto al topic `shellies/#`

Para confirmar que ambos servicios están activos:

```bash
docker compose -f ../docker/docker-compose.yml ps
```

Para ver los mensajes que va imprimiendo el suscriptor Java en tiempo real:

```bash
docker compose -f ../docker/docker-compose.yml logs -f subscriber
```

## Cómo compilar el suscriptor Java manualmente (sin Docker)

Si se quiere compilar y correr el módulo Java fuera de Docker, por ejemplo para debuggear
desde IntelliJ:

```bash
cd src/subscriber
mvn clean package
java -jar target/subscriber-jar-with-dependencies.jar
```

Por defecto se conecta a `tcp://localhost:1883` y se suscribe a `shellies/#`. Esto se puede
sobreescribir con variables de entorno:

| Variable | Default | Descripción |
|---|---|---|
| `MQTT_BROKER_HOST` | `localhost` | Host del broker MQTT |
| `MQTT_BROKER_PORT` | `1883` | Puerto del broker MQTT |
| `MQTT_TOPIC` | `shellies/#` | Topic al que se suscribe |
| `MQTT_CLIENT_ID` | `ioteste-subscriber` | Client ID usado en la conexión MQTT |

## Cómo probar el flujo de mensajes

1. Levantar el entorno: `./up.sh`
2. En una terminal, publicar un mensaje simulado (simula al sensor Shelly H&T):
```bash
   ./send-temp.sh 23.5 60
```
3. Verificar que el suscriptor Java lo recibió:
```bash
   docker compose -f ../docker/docker-compose.yml logs subscriber
```
4. Alternativamente, verificar por consola con el script de suscripción propio (útil para
   confirmar el broker de forma independiente del componente Java):
```bash
   ./receive-temp.sh
```

## Verificación cruzada con un cliente MQTT externo

Se puede usar [MQTT Explorer](http://mqtt-explorer.com/) o [MQTTX](https://mqttx.app/):

1. Conectar la aplicación contra `localhost:1883` (sin autenticación, puerto 1883, sin TLS).
2. Suscribirse al topic `shellies/#`.
3. Publicar un mensaje con `./send-temp.sh` (o `mosquitto_pub` directamente).
4. Confirmar que el mismo mensaje aparece:
    - En la interfaz del cliente MQTT (MQTT Explorer / MQTTX)
    - En los logs del contenedor `ioteste-subscriber`

Esto valida que el broker, el publisher y el suscriptor Java están correctamente integrados.

## Cómo bajar el entorno

```bash
./down.sh    # elimina los contenedores (conserva volúmenes de datos)
./stop.sh    # solo detiene los contenedores, sin eliminarlos
```

## Proyecto Jira

Tablero del equipo: `<PEGAR_LINK_JIRA_ACÁ>`

## Próximos pasos (Iteración 2)

El suscriptor Java evolucionará hacia un receptor que además persiste los datos recibidos
(en lugar de solo imprimirlos por consola), como primer paso hacia el modelo de datos del
producto.