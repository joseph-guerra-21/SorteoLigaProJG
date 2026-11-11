package vista;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import modelo.Partido;

public class VistaConsola {
    private final Scanner teclado = new Scanner(System.in);

    public int mostrarMenu() {
        System.out.println();
        System.out.println("=== Sorteo de la Liga Profesional ===");
        System.out.println("1. Iniciar torneo");
        System.out.println("2. Ver partidos de la ronda actual");
        System.out.println("3. Elegir ganadores y avanzar");
        System.out.println("4. Guardar torneo");
        System.out.println("5. Cargar torneo");
        System.out.println("0. Salir");
        return leerOpcion("Elige una opcion: ", 0, 5);
    }

    public void mostrarRonda(String etapa, List<Partido> partidos) {
        System.out.println();
        System.out.println("=== " + etapa + " ===");
        for (int i = 0; i < partidos.size(); i++) {
            Partido partido = partidos.get(i);
            System.out.printf("%d. %s vs. %s%n", i + 1,
                    partido.getLocal().getNombre(),
                    partido.getVisitante().getNombre());
        }
    }

    public List<Integer> leerGanadores(List<Partido> partidos) {
        List<Integer> elecciones = new ArrayList<>();

        for (Partido partido : partidos) {
            System.out.println();
            System.out.println("1. " + partido.getLocal().getNombre());
            System.out.println("2. " + partido.getVisitante().getNombre());
            System.out.println("0. Cancelar");
            int opcion = leerOpcion("Elige al ganador: ", 0, 2);
            if (opcion == 0) {
                return List.of();
            }
            elecciones.add(opcion);
        }

        return elecciones;
    }

    public void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }

    private int leerOpcion(String pregunta, int minimo, int maximo) {
        while (true) {
            System.out.print(pregunta);
            if (!teclado.hasNextLine()) {
                return 0;
            }

            try {
                int opcion = Integer.parseInt(teclado.nextLine().strip());
                if (opcion >= minimo && opcion <= maximo) {
                    return opcion;
                }
            } catch (NumberFormatException ex) {
                // Mostramos la misma indicacion si se ingresan letras.
            }

            System.out.printf("Escribe un numero del %d al %d.%n", minimo, maximo);
        }
    }
}