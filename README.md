# Reporte Técnico: Comparación entre el Paradigma Imperativo y Funcional para el Procesamiento de Inventario

En este proyecto se realizó el procesamiento de 10.000 registros de hardware. El objetivo fue comparar dos formas de programación: el paradigma imperativo y el paradigma funcional mediante Streams API, analizando aspectos como el rendimiento, la cantidad de código necesaria y la facilidad de mantenimiento.

## Resultados Obtenidos

Las pruebas se realizaron en un entorno local utilizando PostgreSQL ejecutándose en Docker.

| Métrica / Criterio             | Paradigma Imperativo                         | Paradigma Funcional (Streams API)                             |
|--------------------------------|----------------------------------------------|---------------------------------------------------------------|
| Tiempo de ejecución            | 415 ms                                       | 190 ms                                                        |
| Tiempo de respuesta en Postman | 13.04 segundos                               | 7.82 segundos                                                 |
| Líneas de código (aprox.)      | ~35 líneas                                   | ~20 líneas                                                    |
| Recursos utilizados            | Bucles `for`, condicionales `if` y `HashMap` | `filter()`, `collect()`, `groupingBy()`, `reduce()` y `max()` |

---

## Comparación de los Enfoques

### 1. Cantidad de Código

En el paradigma imperativo fue necesario escribir más código, ya que se deben recorrer manualmente las colecciones, validar condiciones y gestionar estructuras auxiliares para almacenar resultados.

Con Streams API se pudo realizar el mismo proceso utilizando menos líneas de código, encadenando las operaciones de filtrado, agrupación y cálculo de manera más directa.

### 2. Legibilidad y Mantenimiento

El enfoque imperativo es más facil de entender cuando el problema es pequeño. Aunque a medida que aumentan las validaciones y los recorridos de datos, el código puede volverse más extenso y difícil de mantener.

El paradigma funcional permite expresar de forma más clara las operaciones que se desean realizar sobre los datos. Además, agregar nuevas condiciones o filtros suele requerir menos cambios en la estructura general del programa.

### 3. Rendimiento

Durante las pruebas realizadas con 10.000 registros, el enfoque funcional obtuvo un mejor tiempo de ejecución, completando el procesamiento en aproximadamente 190 ms frente a los 415 ms del enfoque imperativo.

Aunque ambos métodos resolvieron correctamente el problema, los resultados muestran que Streams API ofrece una solución más compacta y eficiente para este escenario.

---

## Conclusión

Los dos paradigmas permiten obtener los mismos resultados, pero presentan diferencias importantes. El paradigma imperativo brinda un mayor control sobre cada paso del proceso, mientras que el paradigma funcional reduce la cantidad de código y mejora la claridad de las operaciones realizadas.

Según las pruebas efectuadas, el enfoque funcional utilizando Streams API fue la alternativa más eficiente y fácil de mantener para el procesamiento de los registros de inventario.

**Hecho por:** Harvey Fonseca

