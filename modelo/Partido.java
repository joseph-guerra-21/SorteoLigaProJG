package modelo;

import java.io.Serializable;

public class Partido implements Serializable {
    private static final long serialVersionUID = 1L;

    private final Equipo local;
    private final Equipo visitante;

    public Partido(Equipo local, Equipo visitante) {
        this.local = local;
        this.visitante = visitante;
    }

    public Equipo getLocal() {
        return local;
    }

    public Equipo getVisitante() {
        return visitante;
    }
}