package co.edu.uco.arquisw.dominio.transversal.excepciones;

import java.io.Serial;

public class AutorizacionException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 1L;

    public AutorizacionException(String message) {
        super(message);
    }
}