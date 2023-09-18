package co.edu.uco.arquisw.dominio.requisito.modelo;

import co.edu.uco.arquisw.dominio.transversal.utilitario.Mensajes;
import co.edu.uco.arquisw.dominio.transversal.validador.ValidarTexto;
import lombok.Getter;

@Getter
public class RequisitosFinales {
    private String rutaArchivo;

    private RequisitosFinales(String rutaArchivo) {
        setRutaArchivo(rutaArchivo);
    }

    public static RequisitosFinales crear(String rutaArchivo) {
        return new RequisitosFinales(rutaArchivo);
    }

    public void setRutaArchivo(String rutaArchivo) {
        ValidarTexto.validarObligatorio(rutaArchivo, Mensajes.RUTA_ARCHIVO_REQUISITOS_NO_PUEDE_ESTAR_VACIO);
        ValidarTexto.validarPatronURLEsValido(rutaArchivo, Mensajes.PATRON_RUTA_ARCHIVO_REQUISITOS_NO_ES_VALIDO);

        this.rutaArchivo = rutaArchivo;
    }
}