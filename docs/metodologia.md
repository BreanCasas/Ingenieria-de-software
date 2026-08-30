# Metodología Ágil — IoTEste EcoWarm

## Metodología adoptada: Kanban

El equipo adopta **Kanban** como metodología ágil de trabajo a lo largo del proyecto.

### Justificación de la elección

- El equipo trabaja bajo un **flujo continuo de tareas** más que bajo Sprints cerrados con
  planificación y compromiso fijo de alcance al inicio: las iteraciones de 2 semanas definidas
  por la cátedra funcionan como **puntos de corte para entrega y evaluación**, pero el trabajo
  interno del equipo fluye de forma continua entre iteraciones, sin necesidad de una ceremonia
  formal de Sprint Planning ni de comprometerse a un Sprint Backlog cerrado.
- Kanban permite **visualizar el estado del trabajo en todo momento** mediante el tablero,
  sin la sobrecarga de ceremonias (Daily, Sprint Review, Retrospectiva formal) que no aportan
  valor proporcional en un equipo reducido con reuniones informales frecuentes.
- El foco de Kanban en **limitar el trabajo en curso (WIP)** y en el flujo continuo se ajusta
  bien a un contexto académico donde los integrantes combinan este proyecto con otras
  materias y no siempre pueden dedicar bloques de tiempo homogéneos por Sprint.
- Los **entregables por plano e iteración**, definidos por la cátedra, se toman como hitos de
  entrega (similares a un "release" o corte de evaluación), sin que esto implique adoptar Scrum:
  las tareas simplemente deben estar en la columna `Done` al momento del corte de cada
  iteración.

### Principios aplicados

- **Visualizar el flujo de trabajo**: mediante el tablero de Jira con columnas de estado.
- **Limitar el trabajo en curso**: se evita que un integrante tenga más de 1-2 tareas en
  `InProgress` simultáneamente.
- **Gestionar el flujo**: se prioriza mover tareas de forma continua entre columnas antes que
  acumular trabajo en `Todo`.
- **Mejora continua**: al cierre de cada iteración se revisa el cumplimiento de este documento
  y se ajusta si es necesario (ver historial de cambios más abajo).

## Gestión en Jira

### Backlog

El Backlog del proyecto se organiza en **Épicas**, una por cada plano de trabajo definido por la
cátedra:

- **Épica: Diseño del Producto**
- **Épica: Herramientas y Técnicas**
- **Épica: Prototipo**

Cada iteración agrega Historias/Tareas concretas debajo de estas épicas, según los entregables
solicitados para esa iteración. A diferencia de Scrum, estas tareas no se "comprometen" en
bloque al inicio de la iteración: se van agregando y priorizando en el Backlog de forma continua,
y el corte de cada iteración simplemente marca la fecha en la que se evalúa qué quedó en `Done`.

### Board

El tablero del equipo se configura con las siguientes columnas:

| Columna | Significado |
|---|---|
| **Todo** | Tareas priorizadas, listas para tomarse, aún no iniciadas |
| **InProgress** | Tareas en desarrollo activo (con límite de WIP por integrante) |
| **ToTest** | Tareas cuyo desarrollo está terminado y pendientes de verificación/prueba |
| **Done** | Tareas verificadas y completadas |

Este flujo de 4 columnas permite distinguir claramente entre "terminado de programar" y
"verificado que funciona", algo especialmente relevante en este proyecto dado que varios
entregables requieren pruebas end-to-end (por ejemplo, verificar que el flujo MQTT completo
funciona antes de mover una tarea a `Done`).

## Revisión y ajuste de la metodología

Al final de cada iteración, el equipo revisa el cumplimiento de esta metodología: si el flujo de
trabajo definido no se ajusta a la realidad del equipo, se documentan los cambios en este mismo
archivo, indicando la iteración en la que se realizó el ajuste y la justificación correspondiente.

### Historial de cambios

| Iteración | Cambio | Justificación |
|---|---|---|
| 2 | Definición inicial de la metodología: Kanban con tablero de 4 columnas (Todo, InProgress, ToTest, Done) | Primera declaración formal de metodología. Se elige Kanban por sobre Scrum dado el flujo continuo de trabajo del equipo y la carga académica variable de sus integrantes, que dificulta comprometerse a un Sprint Backlog cerrado |