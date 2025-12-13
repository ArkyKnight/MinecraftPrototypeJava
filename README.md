# Minecraft 2D Classic Edition Prototype
## Introduction:
Basic prototype of the classic version of Minecraft:
- Only Building.
- Random terrain generation.
- Inventory.
- Materials Selection.
- Flashlight.

<img width="753" height="143" alt="image" src="https://github.com/user-attachments/assets/2b55919e-6bba-4f33-9d99-c636443853bd"/>

##  General operation:

This prototype is a basic version of Minecraft in 2D aerial view. When the user opens the program, a random world is generated, featuring different colored trees in different positions, making the prototype more replayable. The leaves change every 5 seconds. If the user takes the color that appeared there, that same color is saved in their inventory along with the other colors. The wood and leaves are stored in different slots. If the user presses the number 1, they will select leaves, and if they select 2, they will select wood.
<br>
<br>

![ezgif-81b71b2ef7c8c937](https://github.com/user-attachments/assets/d18e53dc-b5b6-4644-89e0-fbb0bcc8e3c4)


The player also has a flashlight, which can be moved with the mouse to increase their field of vision within the terrain, necessary for observing which resources to obtain.

## Used Technologies:
- Main language: Java.
- Interface and 2D graphics using Swing, AWT, Java 2D API, BufferedImage.
- Mouse and keyboard event handling using MouseListener, MouseMotionListener, and KeyListener.
- Use of Java Sound API, javax.sound.sampled, AudioSystem, AudioInputStream, Clip.
- Concurrency, multithreading using Thread and Thread.sleep().
- Resource management using classpath.
