package co.edu.uco.arquisw.dominio.transversal.validador;

public class ValidarNumero {
    private ValidarNumero() {
    }

    public static boolean validarLongitudEsMayor(int longitud, int limite) {
        return longitud > limite;
    }
}
