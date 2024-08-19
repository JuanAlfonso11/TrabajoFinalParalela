Descripción
Este proyecto es una simulación de intersección de tráfico desarrollada en Java utilizando JavaFX 
para la interfaz gráfica de usuario. El proyecto incluye dos escenarios diferentes de tráfico:

Escenario 1: Simula una intersección de calles simple donde los vehículos pueden moverse en
diferentes direcciones y seguir diversas acciones como doblar a la derecha, izquierda o hacer un giro en U.

Escenario 2: Simula una autopista con múltiples carriles y semáforos que controlan el flujo de 
tráfico. Los vehículos pueden ser normales o de emergencia, y cada tipo de vehículo tiene comportamientos específicos dentro de la simulación.

El proyecto utiliza paralelismo para manejar de manera eficiente el movimiento y la actualización 
gráfica de los vehículos en la intersección.


Características
Interfaz gráfica: Visualización en tiempo real del tráfico en la intersección.
Tipos de vehículos: Soporte para vehículos normales y de emergencia.
Simulación de semáforos: Control de tráfico utilizando semáforos en la intersección.
Paralelismo: Implementación de tareas concurrentes para mover y dibujar los vehículos de manera eficiente.
Escenarios múltiples: Capacidad para seleccionar y simular diferentes escenarios de tráfico


Estructura del Proyecto
HelloApplication.java: Clase principal que inicia la aplicación y maneja la lógica de la interfaz gráfica.
Entidades/: Contiene las clases que representan las entidades del sistema como Vehiculo, Interseccion, Calle, Semaforo, etc.
Escenario2/: Contiene las clases específicas del escenario 2.
enumeraciones/: Contiene los enums utilizados en la simulación como Direccion, EstadoSemaforo, TipoVehiculo, entre otros.


Conclusión y Resultados
La simulación de intersección de tráfico desarrollada en este proyecto demuestra la capacidad 
de Java y JavaFX para manejar sistemas complejos en tiempo real, combinando aspectos visuales con 
lógica de control de tráfico. A través de la implementación de dos escenarios distintos, hemos 
logrado simular diferentes situaciones de tráfico que reflejan problemas comunes en la gestión de intersecciones urbanas y autopistas.

Resultados Clave
Eficiencia en la Simulación: La utilización de técnicas de paralelismo permitió mejorar significativamente 
la eficiencia de la simulación. Al dividir el procesamiento de vehículos en tareas concurrentes, la aplicación es 
capaz de manejar múltiples vehículos simultáneamente sin afectar el rendimiento, asegurando una experiencia fluida para el usuario.

Comportamiento Realista de Vehículos: Los vehículos en la simulación responden de 
manera realista a los semáforos y a la presencia de otros vehículos, especialmente 
los de emergencia. Esto no solo muestra la capacidad del sistema para manejar condiciones 
de tráfico variadas, sino que también destaca la importancia de una lógica bien diseñada en 
el control de semáforos y en la respuesta de los vehículos.

Interacción Usuario-Sistema: La interfaz gráfica desarrollada permite a los usuarios 
experimentar de manera interactiva cómo se gestionan diferentes escenarios de tráfico. 
La posibilidad de agregar vehículos de diferentes tipos y definir sus rutas proporciona 
una herramienta poderosa para experimentar y entender las dinámicas de tráfico.

Escalabilidad y Adaptabilidad: La arquitectura del proyecto es lo suficientemente flexible 
como para permitir la extensión a escenarios más complejos o la integración de nuevos tipos 
de vehículos y reglas de tráfico. Esto abre la puerta para futuros desarrollos y mejoras, 
haciendo de esta simulación una plataforma base sólida para exploraciones más avanzadas en simulación de tráfico.