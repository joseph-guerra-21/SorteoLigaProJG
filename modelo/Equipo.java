package modelo;

import java.io.Serializable;

public class Equipo implements Serializable {
    private static final long serialVersionUID = 1L;

    private final String nombre;

    public Equipo(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }
}