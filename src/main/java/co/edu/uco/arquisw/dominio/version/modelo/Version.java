package co.edu.uco.arquisw.dominio.version.modelo;
import lombok.Getter;
import java.time.LocalDate;

@Getter
public class Version {
    private boolean esFinal;
    private LocalDate fecha;

    private Version(boolean esFinal, LocalDate fecha) {
        setEsFinal(esFinal);
        setFecha(fecha);
    }

    public static Version crear(boolean esFinal, LocalDate fecha)
    {
        return new Version(esFinal,fecha);
    }

    public void setEsFinal(boolean esFinal) {
        this.esFinal = esFinal;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }
}
