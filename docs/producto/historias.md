# Historias de Usuario — EcoWarm

## Historia 1: Precalentar según tarifa horaria

**Como** usuario propietario de una vivienda con calefacción por losa radiante,
**quiero** que EcoWarm programe el precalentamiento de mis habitaciones durante el horario
de tarifa eléctrica más económica,
**para** llegar a la temperatura objetivo sin haber consumido energía en horario pico y así
reducir el costo de mi factura eléctrica.

**Criterios de aceptación:**
- El sistema conoce la franja horaria de tarifa reducida configurada por el usuario.
- Si una habitación está por debajo de su temperatura objetivo antes de que finalice la franja
  de tarifa reducida, el sistema activa su switch dentro de esa franja.
- El usuario puede consultar en qué horario se realizó el último precalentamiento de cada
  habitación.

**Escenario relacionado:** Escenario 1 — Optimización según tarifa multihorario.

---

## Historia 2: Anticipar frío según pronóstico

**Como** usuario que quiere mantener el confort de su hogar sin sobre-consumir energía,
**quiero** que EcoWarm ajuste el encendido de la calefacción según el pronóstico climático de
las próximas 48 horas,
**para** evitar que la temperatura interior caiga antes de que el sistema reaccione, sin
necesidad de intervenir manualmente.

**Criterios de aceptación:**
- El sistema puede consultar un pronóstico climático externo a 48 hs.
- Si el pronóstico indica una caída significativa de temperatura exterior, el sistema adelanta
  la activación de la calefacción en las habitaciones correspondientes.
- El usuario puede ver qué habitaciones fueron ajustadas por esta razón y en qué momento.

**Escenario relacionado:** Escenario 2 — Ajuste por pronóstico climático.

---

## Historia 3: Simular consumo con distinta potencia contratada

**Como** cliente potencial evaluando qué potencia eléctrica contratar,
**quiero** simular distintas configuraciones de habitaciones encendidas bajo una potencia
contratada específica,
**para** entender qué temperaturas son alcanzables antes de decidir mi contrato eléctrico, sin
afectar el funcionamiento real de mi sistema.

**Criterios de aceptación:**
- El usuario puede indicar una potencia contratada hipotética y una cantidad de habitaciones
  activas simultáneamente.
- El sistema devuelve una proyección de temperatura alcanzable por habitación bajo esa
  configuración simulada.
- La simulación no modifica el estado real de los switches ni la configuración vigente del
  sistema.

**Escenario relacionado:** Escenario 3 — Simulación de potencia contratada.