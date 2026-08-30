# Características del Producto — EcoWarm

## 1. Gestión de habitaciones (rooms)

El sistema modela el sitio del cliente como un conjunto de habitaciones. Cada habitación tiene
asociado un termostato (sensor Shelly H&T), un switch (Shelly Pro 1PM) y una temperatura
objetivo configurable (en grados Celsius, con 1 decimal). La configuración de habitaciones se
mantiene de forma persistente.

## 2. Optimización según tarifa eléctrica multihorario

El sistema conoce las franjas horarias de la tarifa UTE multihorario del cliente (contemplando
al menos las dos alternativas de tarifa disponibles) y prioriza el uso de la calefacción durante
los horarios de menor costo, sin dejar de cumplir con la temperatura objetivo configurada.

## 3. Incorporación de pronóstico climático a 48 horas

El sistema puede consultar un servicio externo de pronóstico meteorológico para anticipar
decisiones de calefacción, ajustando el encendido de forma proactiva ante caídas de
temperatura previstas, en vez de reaccionar únicamente a la temperatura medida en el momento.

## 4. Historial de gestión y métricas de desempeño

El sistema registra el histórico de temperaturas de cada habitación con fecha y hora,
permitiendo evaluar el desempeño de la gestión pasada: por ejemplo, cuánto tiempo tarda una
habitación en alcanzar su temperatura objetivo, y cuánto tarda en enfriarse una vez apagada
la calefacción.

## 5. Simulación de configuraciones de consumo

El sistema ofrece una capacidad de simulación que permite proyectar, para una potencia
eléctrica contratada dada, qué temperaturas serían alcanzables en las distintas habitaciones
según cuántas de ellas estén encendidas simultáneamente. Esto permite a clientes actuales o
potenciales evaluar decisiones de contratación de potencia sin afectar el funcionamiento real
del sistema.