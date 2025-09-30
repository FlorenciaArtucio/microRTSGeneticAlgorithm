# MicroRTS Genetic Algorithm

Este proyecto implementa un algoritmo genético para optimizar estrategias de IA en el juego MicroRTS (Micro Real-Time Strategy). Utiliza el framework JMetal para algoritmos evolutivos y se enfoca en evolucionar parámetros de comportamiento para unidades de IA.

## Descripción del Proyecto

El proyecto combina:
- **MicroRTS**: Un motor de juegos de estrategia en tiempo real simplificado
- **Algoritmos Genéticos**: Utilizando JMetal para optimización evolutiva
- **IA Parametrizada**: Un sistema de IA que toma decisiones basadas en parámetros evolutivos

## Arquitectura

### Componentes Principales

#### 1. **GeneticAI** (`com.genetic.GeneticAI`)
- IA principal que implementa comportamientos para diferentes tipos de unidades
- Utiliza parámetros evolutivos para tomar decisiones
- Maneja comportamientos específicos para: Workers, Bases, Barracks, Light, Heavy, y Ranged units

#### 2. **MicroRTSProblem** (`com.genetic.MicroRTSProblem`)
- Define el problema de optimización con 16 variables
- Configura los límites de las variables (bounds)
- Integra la evaluación de fitness

#### 3. **MicroRTSFitness** (`com.genetic.MicroRTSFitness`)
- Evalúa el rendimiento de las soluciones ejecutando partidas
- Calcula la tasa de victoria contra IA de referencia
- Ejecuta múltiples instancias para obtener resultados estadísticamente significativos

#### 4. **GameExecutor** (`com.runner.GameExecutor`)
- Ejecuta partidas entre la IA genética y una IA de referencia
- Maneja la configuración del juego y extracción de resultados
- Soporte para diferentes mapas y configuraciones

### Parámetros Evolutivos (16 variables)

El algoritmo evoluciona los siguientes parámetros:

1. **G_AGGR** [0,1]: Agresividad general
2. **G_EXPAND** [0,1]: Tendencia a expandirse
3. **G_ECON** [0,1]: Enfoque económico
4. **THR_HP_LOW** [0,1]: Umbral de HP bajo
5. **THR_POWER_ADV** [0,1]: Umbral de ventaja de poder
6. **THR_DISTANCE_ENGAGE** [0,1]: Distancia de combate
7. **WORKER_CAP** [4,30]: Límite de trabajadores
8. **T_BARRACKS_TIME** [0,1]: Tiempo para construir barracones
9. **RANGED_RATIO** [0,1]: Proporción de unidades a distancia
10. **EXPAND_TRIGGER_TIME** [0,1]: Tiempo de activación de expansión
11. **W_ATTACK** [0,1]: Ataque de trabajadores
12. **W_HARASS** [0,1]: Acoso de trabajadores
13. **W_DEFEND** [0,1]: Defensa de trabajadores
14. **W_RETREAT** [0,1]: Retirada de trabajadores
15. **W_HARVEST** [0,1]: Cosecha de trabajadores
16. **W_BUILD** [0,1]: Construcción de trabajadores

## Configuración del Algoritmo Genético

- **Algoritmo**: NSGA-II (Non-dominated Sorting Genetic Algorithm II)
- **Población**: 100 individuos
- **Evaluaciones máximas**: 750
- **Crossover**: SBX (Simulated Binary Crossover) con probabilidad 0.8
- **Mutación**: Polynomial Mutation con probabilidad 1/16
- **Selección**: Binary Tournament Selection

## Requisitos

- **Java**: 22 o superior
- **Maven**: Para gestión de dependencias

## Compilación y Ejecución

### Compilar el proyecto
```bash
mvn clean compile
```

### Ejecutar el algoritmo genético
```bash
mvn exec:java
```

### Ejecutar con parámetros específicos
```bash
mvn exec:java -Dexec.args="<argumentos>"
```

## Configuración

### Modificar el número de instancias de evaluación
En `GATest.java`, línea 18:
```java
MicroRTSProblem problem = new MicroRTSProblem(5); // Cambiar el número de instancias
```

### Configurar el mapa
En `MicroRTSFitness.java`, línea 17:
```java
GameExecutor gameExecutor = new GameExecutor(new UnitTypeTable(), "maps/16x16/basesWorkers16x16.xml", false, false, 5000, 20, solution);
```

### Ajustar parámetros del algoritmo
En `GATest.java`:
- `populationSize`: Tamaño de la población
- `maximumEvaluations`: Número máximo de evaluaciones
- `crossoverProbability`: Probabilidad de crossover
- `mutationProbability`: Probabilidad de mutación

## Estructura de Archivos

```
src/main/java/
├── com/genetic/
│   ├── GeneticAI.java          # IA principal con comportamientos evolutivos
│   ├── MicroRTSFitness.java   # Evaluación de fitness
│   ├── MicroRTSProblem.java   # Definición del problema de optimización
│   └── utils/
│       └── Distance.java       # Utilidades para cálculo de distancias
├── com/runner/
│   ├── GameExecutor.java       # Ejecutor de partidas
│   └── GATest.java            # Punto de entrada principal
└── com/utils/
    ├── ExportSolution.java     # Exportación de soluciones
    └── ParamsView.java        # Vista de parámetros de la solución

src/main/resources/
└── maps/
    ├── 8x8/
    │   └── basesWorkers8x8.xml
    └── 16x16/
        └── basesWorkers16x16.xml
```

## Resultados

El algoritmo genera:
- **Soluciones optimizadas**: Conjunto de parámetros que maximizan la tasa de victoria
- **Archivo de exportación**: `solutions.txt` con los mejores parámetros encontrados
- **Métricas de rendimiento**: Tasa de victoria y fitness de cada generación

## Licencia

Este proyecto utiliza las licencias de sus dependencias:
- JMetal: Licencia MIT

## Referencias

- [JMetal Framework](https://github.com/jMetal/jMetal)
- [MicroRTS](https://github.com/santiontanon/microrts)
- [NSGA-II Algorithm](https://ieeexplore.ieee.org/document/996017)
