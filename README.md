\# Sorteo de la Liga Profesional



Proyecto de Programación Orientada a Objetos — Unidad 3  

Autor: Joseph Guerra



Aplicación de consola en Java que sortea un torneo de eliminación directa

con 16 equipos de la LigaPro de Ecuador. Permite elegir a los ganadores

de cada partido y avanzar desde octavos de final hasta el campeón.



\## Cómo ejecutar en Windows



Requisito: JDK 21. Abre CMD en la carpeta del proyecto y ejecuta:



```cmd

javac -encoding UTF-8 Main.java controlador\\\*.java excepciones\\\*.java modelo\\\*.java vista\\\*.java

java Main

```



\## Cómo probar el torneo



1\. Elige \*\*1\*\* para sortear los ocho partidos de octavos.

2\. Elige \*\*3\*\* y selecciona un ganador por partido. Repite en cada ronda

&#x20;  hasta que aparezca el campeón.

3\. Elige \*\*4\*\* para guardar en `torneo.ser`.

4\. Sal con \*\*0\*\*, ejecuta `java Main` otra vez y elige \*\*5\*\* para recuperar

&#x20;  el torneo guardado.



\## Organización del código



\- `modelo`: equipos, partidos, torneo, sorteo recursivo y serialización.

\- `vista`: menú, mensajes y lectura de opciones.

\- `controlador`: conecta el menú con el modelo.

\- `excepciones`: errores de cantidad de equipos y de emparejamiento.



El sorteo mezcla una copia de los equipos y forma un partido por llamada

recursiva. Durante el emparejamiento, comprueba que ningún equipo se

repita en la misma ronda.

