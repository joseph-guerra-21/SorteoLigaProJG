package modelo;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputFilter;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public class ArchivoTorneo {
    private static final Path ARCHIVO = Path.of("torneo.ser");

    public static void guardar(Torneo torneo) throws IOException {
        // Escribimos primero en un temporal para proteger el guardado anterior.
        Path temporal = Files.createTempFile(Path.of("."), "torneo-", ".tmp");
        try {
            try (FileOutputStream archivo = new FileOutputStream(temporal.toFile());
                 ObjectOutputStream salida = new ObjectOutputStream(archivo)) {
                salida.writeObject(torneo);
            }

            Files.move(temporal, ARCHIVO, StandardCopyOption.REPLACE_EXISTING);
        } finally {
            Files.deleteIfExists(temporal);
        }
    }

    public static Torneo cargar() throws IOException, ClassNotFoundException {
        try (FileInputStream archivo = new FileInputStream(ARCHIVO.toFile());
             ObjectInputStream entrada = new ObjectInputStream(archivo)) {
            entrada.setObjectInputFilter(ObjectInputFilter.Config.createFilter(
                    "maxdepth=12;maxrefs=512;maxarray=64;"
                    + "modelo.Torneo;modelo.Partido;modelo.Equipo;"
                    + "java.util.ArrayList;java.lang.Object;java.lang.String;!*"));

            Object contenido = entrada.readObject();
            if (contenido instanceof Torneo torneo) {
                return torneo;
            }

            throw new IOException("El archivo no contiene un torneo.");
        }
    }
}