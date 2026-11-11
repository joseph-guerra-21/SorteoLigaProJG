\# Sorteo de la Liga Profesional



Proyecto de Programación Orientada a Objetos — Unidad 3

Autor: Joseph Guerra



Aplicación de consola en Java que sortea un torneo de eliminación directa

con 16 equipos de la LigaPro de Ecuador, desde octavos hasta la final.



\## Cómo ejecutar en Windows



Requisito: JDK 21. Abre CMD en la carpeta del proyecto y ejecuta:



&#x20;   javac -encoding UTF-8 Main.java

&#x20;   java Main



\## Cómo probar el torneo



1\. Elige 1 para sortear los ocho partidos de octavos.

2\. Elige 3 y selecciona un ganador por partido. Repite hasta obtener un campeón.

3\. Elige 4 para guardar el torneo en torneo.ser.

4\. Sal con 0, ejecuta java Main otra vez y elige 5 para recuperar el torneo.



\## Organización del código



\- modelo: equipos, partidos, rondas, sorteo recursivo y serialización.

\- vista: menú, mensajes y lectura de opciones.

\- controlador: conecta la vista con el modelo.

\- excepciones: errores de cantidad de equipos y de emparejamiento.



El sorteo mezcla una copia de los equipos y forma un partido por llamada

recursiva. Si un equipo aparece dos veces en una ronda, lanza una excepción.

