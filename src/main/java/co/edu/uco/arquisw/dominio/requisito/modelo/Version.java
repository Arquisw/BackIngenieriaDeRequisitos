package co.edu.uco.arquisw.dominio.requisito.modelo;

import co.edu.uco.arquisw.dominio.transversal.formateador.FechaFormateador;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class Version {
    private final boolean esFinal;
    private final boolean estaRechazada;
    private final LocalDate fecha;

    private Version(boolean esFinal, boolean estaRechazada) {
        this.esFinal = esFinal;
        this.estaRechazada = estaRechazada;
        this.fecha = FechaFormateador.obtenerFechaActual();
    }

    public static Version crear(boolean esFinal, boolean estaRechazada) {
        return new Version(esFinal, estaRechazada);
    }
}