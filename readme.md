# TRES EN RAYA
Se pide hacer  en java un juego de  *tres en raya*. 
No es importante que tenga mil opciones y que el diálogo con el usuario sea rebuscado. Los objetivos de esta práctica son:
- Que la lógica del juego descanse en una clase independiente de la entrada salida.
- Practicar ocultación de atributos
- Generar javadoc para la clase de la lógica del juego de forma que seamos reflexivos a la hora de decidir que métodos públicos va a contener esta clase.

Descripción de cosas que debe  cumplir el proyecto:
1. Nos limitamos a Jugador humano vs máquina. Para no liar el código con muchas opciones descartamos la opción humano vs humano. El juego de la máquina es el más interesante para nosotros ya que es el programador el que juega y decide en nombre de la máquina. La máquina puede jugar a muchos niveles de elaboración de jugadas, por ejemplo:
    - simple: hace sus movimientos a una de las casillas libre al azar o a la primera que ve libre por orden de recorrido matriz
    - intermedio: mira antes de mover donde  puede hacer raya 
    - difícil: añade al paso anterior la comprobación de que si no puede hacer raya mira si puede  “tapar” la posible raya de su rival. Incluso hay comportamientos más elaborados que este "difícil" pero hasta aquí llegamos. 

Con la opción “simple” es más que suficiente, luego si te gusta la práctica  puedes mejorarla incorporando los niveles "intermedio" y/o "difícil". ¡Pero primero que funcione con la opción simple! 

2. Trabajar en un proyecto de nombre TresEnRaya
3. Deben escribirse  al menos  dos clases(y quizá no hagan falta más si el código es simple):
    * clase *motor3R.TresEnRaya*: se ocupa de  la lógica  del juego controlando el estado del tablero. No se limita a almacenar una tabla si no que controla la lógica del juego:
       *  lleva el control de fin de partida y puede preguntársele si la partida acabó o no. Si la partida acabó también puede preguntársele si el ganador es el humano o la máquina o hubo empate(tablero lleno sin raya). 
        * Cuando juega la máquina,  es esta clase la que toma la decisión a dónde mover según el nivel de dificultad que se le indica al principio de la partida. 
   * clase *InterfaceConsola*: Interconecta usuario y clase *motor3R.TresEnRaya*. Como describe su nombre, se trata de un  interface de usuario de consola y por tanto contiene el main(). La clase InterfaceConsola recoge los datos del usuario y se los envía a la clase TresEnRaya, por ejemplo cuando el jugador humano realiza un movimiento la clase InterfaceConsola recoge las coordenadas y se las envía a la clase TresEnRaya. Para indicar al usuario que se terminó la partida o quién ganó  la clase Interface se lo pregunta previamente a la clase TresEnRaya ya que es ella la que tiene el control del juego. Puedes asumir que el usuario teclea los datos correctamente, por ejemplo, no teclea las coordenadas de una casilla ya ocupada. Pertenece al paquete por defecto.

Observa que si la clase TresEnRaya está bien escrita podrás reutilizarla para hacer una versión gráfica del Tres en raya. Donde reutilizar significa no tener que modificarla en absoluto y  directamente poder usarla desde  una  clase  que se ocupara del diálogo con el usuario pero de forma gráfica. POR LO TANTO:
* ¡no puede haber ni un println() en la clase TresEnRaya! Tampoco puede usar System.in ni "usar" de ningún modo a la clase InterfaceConsola. TresEnraya en su código desconoce por completo la existencia de InterfaceConsola.
* La clase interface no puede modificar directamente el tablero de juego, se limita a mandar los datos a TresEnRaya para que lo modifique oportunamente TresEnRaya. Todo lo que puede hacer la clase interfaceConsola es "solicitar"  los datos del tablero a TresEnRaya para imprimirlos por pantalla con su propio estilo.

4. Los atributos de las clases deben ser privados y  la clase TresEnRaya debe cumplir el principio de ocultación. Puedes pensar que nadie va a usar tu clase y que no es importante el principio de ocultación, pero se trata de practicar, por otro lado dentro de unos meses si vuelves a usar la clase para hacer un interface gráfico y te da pereza revisar el código el principio de ocultación también protege a tu clase ¡de ti mismo!. 

5. Genera javadoc para la parte pública de la clase motor3R.TresEnRaya. Hacer el javadoc te hará pensar en el diseño de métodos de tu clase. Sólo queremos el javadoc de esta clase, no de InterfaceConsola,  porque la clase motor3R.TresEnRaya es la que queremos que pudiera ser reutilizada por otros programadores y precisa documentación y un diseño entendible por otros. Pensemos que estos "otros" programadores sólo dispondrán el .class de la clase y no les interesa en absoluto el .java, de ahí la necesidad de documentación de uso de la clase. Cuando generes el javadoc, tendrás que escribir el texto de ayuda de forma que otro programador distinto de ti, sin mirar el código java de tu clase, sepa perfectamente como manejarla. Si las explicaciones son buenas y sencillas es porque los métodos públicos están bien diseñados(buenos nombres y funcionalidades claras y precisas), y por tanto son fáciles de entender y manejar. Piensa que si el javadoc no lo entienden bien otros programadores, sea probablemente porque la estructura de tu clase al respecto de los métodos de tu clase no es el adecuado. Puedes pasar tu  javadoc a un compañero para ver si la entiende y sacas conclusiones. 
6. Genera un jar para el proyecto



Puedes utilizar el propio IDE para generar el .jar
Para generar el javadoc quizá mejor la consola como ilustra la siguiente imagen
![ALT](extra/crearjavadocwindows.jpg)

El javadoc debe describir la parte pública de la clase TresEnRaya. Piensa que si un programador tiene sólo TresEnRaya.class (no el .java) y el javadoc debe poder saber utilizarla.

Por ejemplo para el constructor podría ser algo parecido a lo siguiente

![ALT](extra/CapturaJavadoc.jpg)

lo que implica que para crear un tablero de juego haría por ejemplo
 TresEnRaya tablero= new TresEnRaya(‘X’,’O’);

En definitiva el javadoc me debe indicar como manejar la clase sin especificar detalles de implementación interna


SE ENTREGA:  En tu github  de cuenta @iessanclemente.net, un repo con tu solución del proyecto que además contiene un .jar y una carpeta con el javadoc como indica la imagen. No olvides  incluir a lozano@iessanclemente.net como reporter en tu proyecto y tampoco olvides  pegar La url de tu proyecto en la tarea correspondiente del curso moodle. OJO CON LA FECHA DE CIERRE DE LA TAREA.


![ALT](extra/estructura.jpg)



Lo importante de este trabajo es que cumpla los requisitos mencionados, no tiene que ser complicada ni tener una lógica perfecta.

Tienes unos minivideos en este repositorio que te pueden orientar:
- [ejemplodeejecucion.mp4](ejemplodeejecucion.mp4)
- [generarjavadoc.mp4](extra/generarjavadoc.mp4)
- [generarjar.mp4](extra/generarjar.mp4)



