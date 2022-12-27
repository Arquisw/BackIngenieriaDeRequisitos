package co.edu.uco.arquisw.dominio.tipoRequisito.modelo;

import co.edu.uco.arquisw.dominio.transversal.utilitario.Mensajes;
import co.edu.uco.arquisw.dominio.transversal.validador.ValidarTexto;
import lombok.Getter;

@Getter
public class TipoRequisito {

    private String nombre;

    private TipoRequisito(String nombre) {
        setNombre(nombre);
    }
    public static TipoRequisito crear(String nombre)
    {
        return new TipoRequisito(nombre);
    }
    public void setNombre(String nombre) {
        ValidarTexto.validarObligatorio(nombre, Mensajes.NOMBRE_TIPOREQUISITO_VACIO);
        ValidarTexto.validarPatronAlfanumericoEsValido(nombre, Mensajes.PATRON_NOMBRE_TIPOREQUISITO_NO_ES_VALIDO);
        this.nombre = nombre;
    }
}
