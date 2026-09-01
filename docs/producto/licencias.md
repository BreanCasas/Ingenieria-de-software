# Licencias de los componentes utilizados

## 1. Licencia del proyecto

El código desarrollado por el equipo para **IoTEste EcoWarm** se distribuye bajo la licencia **MIT**.

La licencia MIT es una licencia de software libre y permisiva que permite utilizar, copiar, modificar, distribuir y comercializar el software, siempre que se conserve el aviso de copyright y el texto de la licencia.

El texto completo de la licencia utilizada se encuentra en el archivo `LICENSE` ubicado en la raíz del repositorio.

## 2. Componentes de terceros

El proyecto utiliza diferentes herramientas, bibliotecas y componentes de terceros. Cada uno mantiene su propia licencia.

| Componente | Versión | Uso | Licencia |
|---|---|---|---|
| Java / Eclipse Temurin | 25 | Lenguaje y entorno de ejecución de los componentes Java | GPLv2 con Classpath Exception |
| Apache Maven | 3.9 | Compilación, tests y empaquetado | Apache License 2.0 |
| Eclipse Paho MQTT | 1.2.5 | Comunicación MQTT desde los componentes Java | EPL 2.0 / EDL 1.0 |
| Jackson Databind | 2.18.2 | Procesamiento y generación de JSON | Apache License 2.0 |
| Logback | 1.5.13 | Registro de eventos y mensajes del sistema | EPL 1.0 / LGPL 2.1 |
| SLF4J | — | API de logging utilizada por los componentes Java | MIT License |
| Eclipse Mosquitto | 2 | Broker MQTT | EPL 2.0 / EDL 1.0 |
| Docker | — | Contenerización y ejecución del sistema | Apache License 2.0 |

## 3. Uso de los componentes

### Java / Eclipse Temurin

Java 25 se utiliza para desarrollar y ejecutar el generador de eventos y el consumidor MQTT.

La distribución utilizada en los contenedores Docker es **Eclipse Temurin**, basada en OpenJDK.

### Apache Maven

Maven se utiliza para gestionar las dependencias y realizar la compilación y empaquetado de los módulos Java.

En particular, los Dockerfiles utilizan una imagen de Maven para realizar el proceso de compilación dentro del contenedor.

### Eclipse Paho MQTT

Paho MQTT se utiliza como cliente MQTT en los componentes Java.

El generador utiliza Paho para publicar los eventos de temperatura y el consumidor utiliza Paho para suscribirse y recibir dichos eventos.

### Jackson

Jackson se utiliza para trabajar con los mensajes JSON intercambiados por el sistema y para persistir información en formato JSON.

### Logback y SLF4J

SLF4J proporciona la API de logging y Logback proporciona la implementación utilizada por los componentes Java.

Se utilizan para registrar información de ejecución, conexiones MQTT, mensajes recibidos, eventos publicados y errores.

### Eclipse Mosquitto

Mosquitto funciona como broker MQTT del sistema.

Se ejecuta mediante Docker Compose utilizando la imagen:

```text
eclipse-mosquitto:2
```

### Docker

Docker se utiliza para ejecutar los distintos componentes del sistema en contenedores y para permitir un entorno reproducible de ejecución y compilación.

## 4. Dependencias transitivas

Las dependencias declaradas directamente en los archivos `pom.xml` pueden incorporar otras dependencias de manera transitiva.

Estas dependencias son gestionadas automáticamente por Maven.

Para consultar el árbol completo de dependencias del proyecto se puede utilizar:

```bash
mvn dependency:tree
```

En caso de una futura distribución comercial o publicación Open Source del producto, se deberá realizar una revisión completa de las dependencias directas y transitivas para verificar el cumplimiento de sus respectivas licencias.

## 5. Código propio y código de terceros

El código desarrollado específicamente por el equipo para **IoTEste EcoWarm** está cubierto por la licencia MIT indicada en el archivo `LICENSE`.

Las bibliotecas, herramientas e imágenes de terceros utilizadas por el proyecto no pasan a estar bajo la licencia MIT. Cada componente conserva la licencia establecida por sus respectivos autores.

Este documento corresponde al estado del proyecto durante la **Iteración 2** y deberá actualizarse si se incorporan nuevos componentes o se modifican las versiones utilizadas.