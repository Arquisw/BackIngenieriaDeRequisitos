package co.edu.uco.arquisw.dominio.transversal.excepciones;

import java.io.Serial;

public class AccionExcepcion extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 1L;

    public AccionExcepcion(String message) {
        super(message);
    }
}