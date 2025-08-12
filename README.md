Introducción

Este informe documenta el proceso de desarrollo de la implementación de la
aplicación de clustering humano. El objetivo principal del proyecto es
representar grupos de personas en base a las afinidades de estas, según sus
intereses en distintas temáticas en un entorno de programación.

Implementación

Para la implementación del juego se utilizó Java como lenguaje de
programación, siguiendo el patrón de diseño Model View Presenter para separar
las vistas, desarrolladas con Swing y WindowBuilder, del código de negocios.
Estructura de directorios:

● bfs/: Contiene los métodos necesarios para obtener las componentes
conexas de un grafo y poder crear los grupos.

● model/: Contiene el código de negocios de la aplicación.

● presenter/: Contiene el código de la clase Presenter, que hace de
intermediario entre la interfaz gráfica y el modelo de negocios.

● test/: Contiene los tests unitarios de las clases dentro del paquete model.

● view/: Contiene el diseño de la interfaz gráfica.

Componentes principales

● model/Grafo.java

● model/Aplicacion.java

● presenter/Presenter.java

● view/View.java

Decisiones de interfaz

Se optó por una interfaz gráfica que permite el ingreso manual de los datos
necesarios para la aplicación, generando distintas vistas para mostrar los
resultados del problema. Al iniciar la aplicación se mostrará la vista
perteneciente a la clase view, la cual contiene un formulario para ingresar
personas pudiendo añadir la cantidad de personas que el usuario desee,
ingresando su nombre y el nivel de intereses de cada una de las temáticas, las
personas agregadas podrán visualizarse dentro de una tabla. La vista View
también cuenta con un botón para crear grupos y un comboBox con opciones
para seleccionar la cantidad de grupos que se desean crear. Al hacer clic en el
botón “Crear grupos”, se mostrará la vista que pertenece a la clase viewGrupos,
la cual muestra los grupos generados en distintas tablas. También se encuentran
los botones “volver” el cual permite volver a la vista principal y el botón “ver
estadísticas” el cual muestra la vista perteneciente a la clase viewEstadisticas
que contiene los promedios de interés por cada temática.

Decisiones lógicas

Para la implementación de la lógica optamos por el uso de la clase Aplicación
como clase principal representando el código de negocios ante el Presenter. Esta
clase cuenta con un Grafo completo, el cual implementamos mediante una
matriz de adyacencia de tipo Integer. Con la diferencia de que en esta matriz no
representamos las aristas existentes con un uno, sino que las representamos con
el peso de la arista. El peso de las aristas va de 0 a 16 y null en caso que la arista
no exista, si bien en este caso necesitamos un grafo completo, no deberían
existir aristas entre un vértice consigo mismo. Dentro de la clase Aplicación
también contamos con un ArrayList de tipo Persona, que va a contener a todas
las personas que agregue el usuario y las va a relacionar con los vértices del
grafo según su posición. Los pesos de las aristas se representan como la
similaridad entre las personas. Estas personas cuentan con cinco variables. La
variable nombre de tipo String y 4 variables enteras que representan el interés
del 1 al 5 de las distintas temáticas, en este caso, d de deportes, m de música, e
de espectáculo y c de ciencia. La clase aplicación cuenta con métodos que
permiten crear un grafo completo, eliminar la arista más pesada de un grafo,
generar grupos según la similaridad entre las personas, etc.
Dentro de las clases de negocio se encuentra la clase AGM la cual contiene un
método que a partir de un grafo genera el árbol generador mínimo de este.
En el package presenter se encuentra la clase que sirve de presenter entre la
interfaz y las clases de negocio, este se encarga de usar los datos ingresados en
la interfaz para generar el grafo de personas mediante los métodos del modelo
de negocio, también se encarga de aplicar AGM al grafo y generar los grupos de
personas para de nuevo enviarselos a la interfaz, la cual se ocupa de mostrar
estos datos al usuario.

Conclusión

Durante el desarrollo de este trabajo práctico nos hemos encontrado con muchos
conflictos, a los cuales encontramos solución repasando lo visto en clase,
investigando por nuestra cuenta y probando distintas opciones para encontrar el
mejor resultado posible. Luego de este trabajo práctico nos encontramos con
nociones más claras de la arquitectura de diseño MVP, la cual no sabíamos
manejar muy bien pero logramos conseguirlo. También pudimos poner a prueba
nuevas técnicas de Swing y WindowBuilder, como el uso de tablas, cuadros de
diálogo, radioButtons, etc. También comprendimos a fondo el problema del
árbol generador mínimo y a generar tests para sentirnos más seguros con
nuestro código.
