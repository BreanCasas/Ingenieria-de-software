# Escenarios de Uso — EcoWarm

## Escenario 1: Optimización según tarifa multihorario

María tiene calefacción por losa radiante en 3 habitaciones de su casa, con un plan de tarifa
UTE multihorario que tiene un horario nocturno más económico. EcoWarm conoce la franja
horaria de tarifa reducida y programa el precalentamiento de las habitaciones durante ese
horario, de forma que al llegar la mañana las habitaciones ya estén a la temperatura objetivo
sin haber consumido energía en horario pico.

**Actores:** Usuario final (María), sistema EcoWarm, switches Shelly Pro 1PM.
**Disparador:** Se acerca el horario de tarifa reducida y alguna habitación está por debajo de
su temperatura objetivo.
**Resultado esperado:** Las habitaciones alcanzan su temperatura objetivo consumiendo
energía mayormente en el horario de tarifa más económica.

---

## Escenario 2: Ajuste por pronóstico climático

Se pronostica una ola de frío para las próximas 48 horas. EcoWarm consulta el servicio de
pronóstico del tiempo y anticipa el encendido de la calefacción en las habitaciones con mayor
pérdida térmica (por ejemplo, aquellas con sensores exteriores que reportan temperaturas más
bajas), ajustando el consumo de forma proactiva en vez de reactiva.

**Actores:** Sistema EcoWarm, servicio externo de pronóstico, sensores exteriores de
temperatura/humedad (opcionales).
**Disparador:** El pronóstico a 48 hs indica una caída significativa de temperatura exterior.
**Resultado esperado:** El sistema adelanta decisiones de calefacción antes de que la
temperatura interior baje, evitando picos de consumo reactivo.

---

## Escenario 3: Simulación de potencia contratada

Un cliente potencial evalúa contratar una potencia eléctrica menor para ahorrar en la tarifa
fija mensual. Antes de decidir, usa la capacidad de simulación de EcoWarm para conocer qué
temperatura sería alcanzable en cada habitación si solo pudiera tener encendidas 2 de las 4
habitaciones simultáneamente con esa potencia contratada, ayudándolo a tomar una decisión
informada sobre su contrato eléctrico.

**Actores:** Cliente potencial, sistema de simulación de EcoWarm.
**Disparador:** El cliente solicita simular un escenario de potencia contratada distinto al
actual.
**Resultado esperado:** El sistema devuelve una proyección de temperaturas alcanzables por
habitación bajo la configuración simulada, sin afectar el funcionamiento real del sistema.