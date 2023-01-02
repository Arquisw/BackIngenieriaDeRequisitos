package co.edu.uco.arquisw.dominio.fase.modelo;

import co.edu.uco.arquisw.dominio.transversal.formateador.FechaFormateador;
import lombok.Getter;
import java.time.LocalDate;

@Getter
public class Version {
    private final boolean esFinal;
    private final LocalDate fecha;

    private Version(boolean esFinal) {
        this.esFinal = esFinal;
        this.fecha = FechaFormateador.obtenerFechaActual();
    }

    public static Version crear(boolean esFinal) {
        return new Version(esFinal);
    }
}