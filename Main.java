import controlador.ControladorTorneo;
import vista.VistaConsola;

public class Main {
    public static void main(String[] args) {
        VistaConsola vista = new VistaConsola();
        ControladorTorneo controlador = new ControladorTorneo(vista);
        controlador.ejecutar();
    }
}