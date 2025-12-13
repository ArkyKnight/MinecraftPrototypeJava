# Minecraft 2D Classic Edition Prototype
## Introduccion:
Prototipo basico de la version clasica de Minecraft contiene:
- Solo construccion.
- Generacion Aleatoria de terreno.
- Inventario.
- Seleccion de materiales.
- Linterna.

<img width="753" height="143" alt="image" src="https://github.com/user-attachments/assets/2b55919e-6bba-4f33-9d99-c636443853bd"/>

## Funcionamiento general:

Este prototipo es una version basica de Minecraft en 2D vista aerea, cuando el usuario abre el programa, un mundo aleatorio es generado, este cuenta con distintos arboles de colores, los arboles tienen distintas posiciones, haciendo mas rejugable el prototipo, las ojas cambian cada 5 segundos, si el usuario toma el color que aparecio ahi, ese mismo color se guarda en su inventario junto con los demas colores, la madera y las hojas se guardan en slots diferentes, si presiona el numero 1, el usuario seleccionara hojas, y si selecciona 2 sera madera.
<br>
<br>

![ezgif-81b71b2ef7c8c937](https://github.com/user-attachments/assets/d18e53dc-b5b6-4644-89e0-fbb0bcc8e3c4)


El jugador tambien cuenta con una linterna, la cual podra mover con el mouse para aumentar su campo de vision dentro de el terreno, necesaria para observar que materiales obtener.

## Tecnologias usadas:
- Lenguaje principal java.
- Interfaz y graficos 2D mediante swing, awt, Java 2D Api, BufferedImage.
- Manejo de eventos del raton y teclado mediante MouseListener, MouseMotionListener y KeyListener.
- Uso de Java Sound Api, javax.sound.sampled, AudioSystem, AudioInputStream, Clip.
- Concurrencia, Multithreading uso de Thread y Thread.sleep().
- Gestion de recursos mediante classpath.
