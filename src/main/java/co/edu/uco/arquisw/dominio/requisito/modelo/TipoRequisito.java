package co.edu.uco.arquisw.dominio.requisito.modelo;

import co.edu.uco.arquisw.dominio.transversal.utilitario.Mensajes;
import co.edu.uco.arquisw.dominio.transversal.utilitario.NumeroConstante;
import co.edu.uco.arquisw.dominio.transversal.validador.ValidarTexto;
import lombok.Getter;

@Getter
public class TipoRequisito {
    private String nombre;

    private TipoRequisito(String nombre) {
        setNombre(nombre);
    }

    public static TipoRequisito crear(String nombre) {
        return new TipoRequisito(nombre);
    }

    private void setNombre(String nombre) {
        ValidarTexto.validarObligatorio(nombre, Mensajes.NOMBRE_TIPO_REQUISITO_VACIO);
        ValidarTexto.validarPatronAlfanumericoEsValido(nombre, Mensajes.PATRON_NOMBRE_TIPO_REQUISITO_NO_ES_VALIDO);
        ValidarTexto.validarLongitudValida(nombre, NumeroConstante.DOCE_ENTERO, Mensajes.obtenerNombreTipoRequisitoNoPuedeExcederLongitud(NumeroConstante.DOCE_ENTERO));

        this.nombre = nombre;
    }
}