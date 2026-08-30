# Documento de Visión del Producto — v2

**Proyecto:** IoTEste EcoWarm
**Iteración:** 2 (actualiza la Visión v1 de la Iteración 1)
**Fecha:** Agosto 2026

---

## 1. Declaración de Visión (Plantilla de Moore)

> **Para** hogares y oficinas con calefacción por losa radiante que buscan maximizar su confort y también optimizar su consumo eléctrico,
> **el software IoTEste EcoWarm**
> **es un** componente de una solución de domótica que realiza la gestión inteligente de sensores y switches,
> **a diferencia de** otras soluciones de domótica que sólo automatizan algunas acciones,
> **nuestro producto** realiza la gestión del consumo eléctrico en forma inteligente, optimizando el consumo según las tarifas disponibles en nuestro mercado.

*(Declaración de Visión definida por la dirección de IoTEste para esta iteración, tomada como base para refinar el alcance funcional del producto.)*

---

## 2. Evolución respecto a la Visión v1

La Visión v1 planteaba un alcance general de automatización de hogares/oficinas sobre dispositivos Shelly, sin un foco de negocio específico. La v2 **acota y concreta** ese alcance:

- El foco pasa a ser específicamente la **calefacción por losa radiante eléctrica**, no la convencional en general.
- Se incorpora como diferenciador central la **optimización según tarifas eléctricas** (tarifa UTE multihorario), en lugar de solo independencia de la nube del fabricante.
- El producto adopta un nombre concreto: **EcoWarm**, como componente de la solución de domótica IoTEste.
- Se define el modelo de datos concreto del dominio: el sitio se organiza en **habitaciones (rooms)**, cada una con su termostato (Shelly H&T) y su switch asociado (Shelly Pro 1PM), con una temperatura objetivo configurable.

---

## 3. Análisis de capacidades de los dispositivos

*(Análisis desarrollado en la Iteración 1, vigente y ampliado con el rol funcional de cada dispositivo dentro del modelo de habitaciones de EcoWarm.)*

### 3.1 Shelly Pro 1PM

| Aspecto | Detalle |
|---|---|
| Función principal | Interruptor (switch) de corriente eléctrica con medición de energía integrada |
| Mediciones | Potencia activa (W), voltaje (V), corriente (A), energía acumulada (kWh), frecuencia de línea |
| Actuación | Encendido/apagado remoto del circuito controlado (relé) |
| Conectividad | Wi-Fi; protocolo Gen2 RPC sobre MQTT y HTTP/REST |
| Rol en EcoWarm | **Switch de habitación**: controla el circuito de calefacción de esa habitación específica |

### 3.2 Shelly H&T Gen 3

| Aspecto | Detalle |
|---|---|
| Función principal | Sensor ambiental de temperatura y humedad |
| Mediciones | Temperatura (°C), humedad relativa (%), nivel de batería |
| Alimentación | A batería — reporta periódicamente, no en streaming continuo |
| Conectividad | Wi-Fi; protocolo Gen2 RPC sobre MQTT |
| Rol en EcoWarm | **Termostato de habitación**: mide la temperatura real, comparada contra la temperatura objetivo configurada |

### 3.3 Implicancias de diseño

- La naturaleza **a batería** del H&T Gen 3 implica que el sistema debe tolerar lecturas espaciadas en el tiempo, no telemetría continua.
- El Pro 1PM, con alimentación de línea, permite actuación en tiempo real — es el componente natural para el control de la calefacción de cada habitación.
- Ambos dispositivos comparten transporte (MQTT) y formato (JSON), validando la arquitectura basada en un broker MQTT común y un modelo de habitaciones que asocia un termostato y un switch por cada una.

---

## 4. Problema que resuelve

Los usuarios de dispositivos IoT domésticos hoy dependen de aplicaciones propietarias por fabricante, sin automatización cruzada real ni conciencia del costo eléctrico de sus decisiones. EcoWarm resuelve esto centralizando la gestión de calefacción por habitación y tomando en cuenta la tarifa eléctrica vigente para decidir cuándo calentar, algo que las soluciones de domótica genéricas no contemplan.

## 5. Ventaja competitiva

- Optimización activa del consumo según **tarifa multihorario**, no solo automatización básica de encendido/apagado.
- Independencia de la nube del fabricante, sobre una arquitectura abierta basada en MQTT estándar.
- Modelo de datos pensado desde el negocio (habitaciones, temperatura objetivo) y no solo desde el dispositivo, lo que habilita funcionalidades futuras como simulación de consumo, uso de pronóstico climático, e historial de gestión pasada.
- Extensible a nuevas fuentes de datos (sensores exteriores, pronóstico a 48 hs) y a integración conversacional vía GenAI/LLM como diferenciador a futuro.

## 6. Contexto de mercado a considerar

- Los clientes utilizan **tarifa UTE multihorario**, con al menos dos alternativas de tarifa a evaluar.
- Existe la posibilidad de incorporar **sensores exteriores** de temperatura/humedad y **pronósticos meteorológicos a 48 hs**.
- Se valora contar con **historial de gestión pasada** (tiempos de calentamiento/enfriamiento por habitación).
- Se contempla una futura **capacidad de simulación**, para proyectar temperaturas alcanzables según potencia contratada y habitaciones activas simultáneamente.
- Como ventaja diferencial a futuro, se evalúa la incorporación de **GenAI/LLM** para interacción conversacional con el sistema.

## 7. Alcance de esta versión (v2)

Esta iteración se enfoca en cerrar la definición de escenarios, historias de usuario y características del producto; establecer la metodología ágil de trabajo; y extender el prototipo técnico con persistencia de la configuración de habitaciones y el histórico de temperaturas, sentando las bases para las decisiones de gestión inteligente que se incorporarán en iteraciones futuras.