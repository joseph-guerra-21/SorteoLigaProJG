package controlador;

import excepciones.NumeroEquiposInvalidoException;
import excepciones.SorteoInvalidoException;
import java.io.IOException;
import java.util.List;
import modelo.ArchivoTorneo;
import modelo.Equipo;
import modelo.Torneo;
import vista.VistaConsola;

public class ControladorTorneo {
    private final VistaConsola vista;
    private Torneo torneo;

    public ControladorTorneo(VistaConsola vista) {
        this.vista = vista;
    }

    public void ejecutar() {
        int opcion = vista.mostrarMenu();

        while (opcion != 0) {
            try {
                switch (opcion) {
                    case 1 -> iniciarTorneo();
                    case 2 -> mostrarTorneo();
                    case 3 -> avanzarRonda();
                    case 4 -> guardarTorneo();
                    case 5 -> cargarTorneo();
                }
            } catch (NumeroEquiposInvalidoException | SorteoInvalidoException ex) {
                vista.mostrarMensaje("Error en el torneo: " + ex.getMessage());
            } catch (IOException | ClassNotFoundException ex) {
                vista.mostrarMensaje("Error con el archivo torneo.ser: " + ex.getMessage());
            }

            opcion = vista.mostrarMenu();
        }

        vista.mostrarMensaje("Programa finalizado.");
    }

    private void iniciarTorneo()
            throws NumeroEquiposInvalidoException, SorteoInvalidoException {
        List<Equipo> equipos = List.of(
                "Aucas", "Barcelona SC", "Delfín SC", "Deportivo Cuenca",
                "Emelec", "Guayaquil City", "Independiente del Valle", "LDU Quito",
                "Leones del Norte", "Libertad FC", "Macará", "Manta FC",
                "Mushuc Runa", "Orense SC", "Técnico Universitario", "Universidad Católica")
                .stream()
                .map(Equipo::new)
                .toList();

        torneo = new Torneo(equipos);
        mostrarTorneo();
    }

    private void mostrarTorneo() throws SorteoInvalidoException {
        verificarTorneo();
        vista.mostrarRonda(torneo.getEtapaActual(), torneo.getPartidosActuales());

        if (torneo.getCampeon() != null) {
            vista.mostrarMensaje("Campeon: " + torneo.getCampeon().getNombre());
        }
    }

    private void avanzarRonda()
            throws NumeroEquiposInvalidoException, SorteoInvalidoException {
        verificarTorneo();

        if (torneo.getCampeon() != null) {
            vista.mostrarMensaje("El torneo ya termino.");
            return;
        }

        List<Integer> resultados = vista.leerGanadores(torneo.getPartidosActuales());

        // Una lista vacia indica que se cancelo la seleccion.
        if (resultados.isEmpty()) {
            vista.mostrarMensaje("Seleccion cancelada.");
            return;
        }

        torneo.avanzar(resultados);
        mostrarTorneo();
    }

    private void guardarTorneo() throws IOException, SorteoInvalidoException {
        verificarTorneo();
        ArchivoTorneo.guardar(torneo);
        vista.mostrarMensaje("Torneo guardado en torneo.ser.");
    }

    private void cargarTorneo()
            throws IOException, ClassNotFoundException, SorteoInvalidoException {
        torneo = ArchivoTorneo.cargar();
        vista.mostrarMensaje("Torneo cargado.");
        mostrarTorneo();
    }

    private void verificarTorneo() throws SorteoInvalidoException {
        if (torneo == null) {
            throw new SorteoInvalidoException("Primero inicia o carga un torneo.");
        }
    }
}