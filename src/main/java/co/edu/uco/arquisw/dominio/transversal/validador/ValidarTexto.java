package co.edu.uco.arquisw.dominio.transversal.validador;

import co.edu.uco.arquisw.dominio.transversal.excepciones.LongitudExcepcion;
import co.edu.uco.arquisw.dominio.transversal.excepciones.PatronExcepcion;
import co.edu.uco.arquisw.dominio.transversal.excepciones.ValorInvalidoExcepcion;
import co.edu.uco.arquisw.dominio.transversal.excepciones.ValorObligatorioExcepcion;
import co.edu.uco.arquisw.dominio.transversal.utilitario.TextoConstante;

public class ValidarTexto {
    private static final String LETRAS_Y_ESPACIOS = "^[a-zA-ZáéíóúÁÉÍÓÚÄëËïÏöÖüÜñÑ ]*$";
    private static final String ALFANUMERICO = "^[a-zA-ZáéíóúäÄëËïÏöÖüÜÁÉÍÓÚñÑ .\\-_+*/#$!=,;()\"%':?@0-9]*$";

    private ValidarTexto() {
    }

    public static void validarObligatorio(String valor, String mensaje) {
        if (cadenaEstaVacia(valor)) {
            throw new ValorObligatorioExcepcion(mensaje);
        }
    }

    public static void validarPatronAlfanumericoEsValido(String valor, String mensaje) {
        if (!cadenaEsAlfanumerica(valor)) {
            throw new PatronExcepcion(mensaje);
        }
    }

    public static void validarPatronTextoEsValido(String valor, String mensaje) {
        if (!cadenaLetrasYEspacios(valor)) {
            throw new PatronExcepcion(mensaje);
        }
    }

    public static void validarLongitudValida(String valor, int longitudLimite, String mensaje) {
        if(ValidarNumero.validarLongitudEsMayor(valor.length(), longitudLimite)) {
            throw new LongitudExcepcion(mensaje);
        }
    }

    public static boolean cadenaEstaVacia(String string) {
        return cadenaEsNula(string) || "".equals(aplicarTrim(string));
    }

    public static boolean cadenaEsNula(String string) {
        return ValidarObjeto.esNulo(string);
    }

    public static String aplicarTrim(String string) {
        return obtenerValorPorDefecto(string.trim());
    }

    public static String obtenerValorPorDefecto(String string) {
        return ValidarObjeto.obtenerValorPorDefecto(string, TextoConstante.VACIO);
    }

    public static boolean cadenaAceptaElPatron(String string, String pattern) {
        return aplicarTrim(string).matches(pattern);
    }

    public static boolean cadenaLetrasYEspacios(String string) {
        return cadenaAceptaElPatron(string, LETRAS_Y_ESPACIOS);
    }

    public static boolean cadenaEsAlfanumerica(String string) {
        return cadenaAceptaElPatron(string, ALFANUMERICO);
    }
}