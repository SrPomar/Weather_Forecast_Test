# Weather_Forecast_Test - Resolución del ejercicio

### ¿Qué has empezado implementando y por qué?

El primer paso ha sido encontrar una solución a un bug de casteo del código proporcionado que impedía la ejecución del mismo, ya que para mi poder seguir el flujo del programa es la forma más sencilla de comprender cómo funciona y qué cambios se pueden implementar. Una vez hecho eso, me he centrado en limpiar y refactorizar el método getCityWeather, para mejorar su legibilidad.

### ¿Qué problemas te has encontrado al implementar los tests y cómo los has solventado?

Los tests que he implementado son vergonzosamente básicos, principalmente por mi propia falta de imaginación. En consecuencia, no he tenido ninguna dificultad real a la hora de implementarlos.

### ¿Qué componentes has creado y por qué?

Dos métodos: parseGeocode() y callOpenMeteo(), con sus correspondientes tests unitarios. Idealmente habría creado un tercer método para gestionar las peticiones html, y una forma de especificar una fecha en concreto para solicitar un pronóstico determinado

### Si has utilizado dependencias externas, ¿por qué has escogido esas dependencias?

No, no he considerado necesario el uso de dependencias externas más allá de las que ya venían incluidas en el ejemplo.

### ¿Has utilizado  streams, lambdas y optionals de Java 8? ¿Qué te parece la programación funcional?

No, no he utilizado ninguna de las tres cosas. No se me enseñaron en clase y en ninguno de mis proyectos personales las he necesitado. En cuanto a la programación funcional, creo que facilita mucho el trabajo de programar, y mejora la legibilidad del código significativamente.

### ¿Qué piensas del rendimiento de la aplicación? 

En mi máquina y con mi conexión a internet, la ejecución del test integral tarda alrededor de un segundo, suficientemente rápido como para no resultar molesto para el usuario. Si fuera algo que debe procesar miles de solicitudes por segundo otro gallo cantaría.

### ¿Qué harías para mejorar el rendimiento si esta aplicación fuera a recibir al menos 100 peticiones por segundo?

Implementar una caché, los pronósticos meteorológicos no se actualizan con demasiada frecuencia y sospecho que la mayor parte del tiempo de espera es debido a esperar una respuesta de las APIs externas. 

### ¿Cuánto tiempo has invertido para implementar la solución? 

Un poco menos de cinco horas, dos de las cuales han sido para descubrir por qué el código original no funcionaba (cuando, a todas luces, debería) y cómo solucionarlo.

### ¿Crees que en un escenario real valdría la pena dedicar tiempo a realizar esta refactorización?

Sí. Considero que todo el código realizado profesionalmente debería estar bien estructurado y comentado para ser legible. Si ya me cuesta a mi entender cómo funciona una chapuza que hice para mi mismo hace seis meses, no me puedo imaginar la de tiempo que perdería en entender una chapuza que hizo otra persona hace cuatro años, y que ya ni trabaja en la empresa.
