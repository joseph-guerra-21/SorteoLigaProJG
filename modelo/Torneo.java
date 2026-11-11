package modelo;

import excepciones.NumeroEquiposInvalidoException;
import excepciones.SorteoInvalidoException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Torneo implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final String[] ETAPAS = {
        "Octavos de final", "Cuartos de final", "Semifinales", "Final"
    };

    private final List<List<Partido>> rondas = new ArrayList<>();
    private Equipo campeon;

    public Torneo(List<Equipo> equipos)
            throws NumeroEquiposInvalidoException, SorteoInvalidoException {
        if (equipos.size() != 16) {
            throw new NumeroEquiposInvalidoException(
                    "El torneo debe comenzar con exactamente 16 equipos.");
        }

        rondas.add(Sorteo.sortear(equipos));
    }

    public String getEtapaActual() {
        return ETAPAS[rondas.size() - 1];
    }

    public List<Partido> getPartidosActuales() {
        return List.copyOf(rondas.get(rondas.size() - 1));
    }

    public Equipo getCampeon() {
        return campeon;
    }

    public void avanzar(List<Integer> resultados)
            throws NumeroEquiposInvalidoException, SorteoInvalidoException {
        if (campeon != null) {
            throw new SorteoInvalidoException("El torneo ya termino.");
        }

        List<Partido> partidos = getPartidosActuales();
        if (resultados.size() != partidos.size()) {
            throw new SorteoInvalidoException(
                    "Debes elegir un ganador por cada partido.");
        }

        List<Equipo> ganadores = new ArrayList<>();
        for (int i = 0; i < partidos.size(); i++) {
            Partido partido = partidos.get(i);
            int opcion = resultados.get(i);

            if (opcion == 1) {
                ganadores.add(partido.getLocal());
            } else if (opcion == 2) {
                ganadores.add(partido.getVisitante());
            } else {
                throw new SorteoInvalidoException(
                        "Elige 1 para el local o 2 para el visitante.");
            }
        }

        // Si solo queda un ganador, es el campeon de la final.
        if (ganadores.size() == 1) {
            campeon = ganadores.get(0);
        } else {
            rondas.add(Sorteo.sortear(ganadores));
        }
    }
}