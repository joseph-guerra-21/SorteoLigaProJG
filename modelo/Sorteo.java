package modelo;

import excepciones.NumeroEquiposInvalidoException;
import excepciones.SorteoInvalidoException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

public class Sorteo {
    public static List<Partido> sortear(List<Equipo> equipos)
            throws NumeroEquiposInvalidoException, SorteoInvalidoException {
        int cantidad = equipos.size();
        if (cantidad != 16 && cantidad != 8 && cantidad != 4 && cantidad != 2) {
            throw new NumeroEquiposInvalidoException(
                    "La ronda debe tener 16, 8, 4 o 2 equipos. Recibidos: " + cantidad);
        }

        // Mezclamos una copia para conservar el orden de la lista original.
        List<Equipo> mezclados = new ArrayList<>(equipos);
        Collections.shuffle(mezclados);

        List<Partido> partidos = new ArrayList<>();
        emparejar(mezclados, 0, new HashSet<>(), partidos);
        return partidos;
    }

    private static void emparejar(List<Equipo> equipos, int posicion,
            Set<String> usados, List<Partido> partidos)
            throws SorteoInvalidoException {
        // Caso base: ya se emparejaron todos los equipos.
        if (posicion == equipos.size()) {
            return;
        }

        Equipo local = equipos.get(posicion);
        Equipo visitante = equipos.get(posicion + 1);

        for (Equipo equipo : List.of(local, visitante)) {
            String nombre = equipo.getNombre().strip();
            if (nombre.isEmpty()) {
                throw new SorteoInvalidoException("Hay un equipo sin nombre.");
            }
            if (!usados.add(nombre.toLowerCase(Locale.ROOT))) {
                throw new SorteoInvalidoException("Equipo repetido en la ronda: " + nombre);
            }
        }

        partidos.add(new Partido(local, visitante));
        emparejar(equipos, posicion + 2, usados, partidos);
    }
}