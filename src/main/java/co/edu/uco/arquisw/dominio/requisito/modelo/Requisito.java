package co.edu.uco.arquisw.dominio.requisito.modelo;

import co.edu.uco.arquisw.dominio.tipoRequisito.modelo.TipoRequisito;
import co.edu.uco.arquisw.dominio.transversal.utilitario.Mensajes;
import co.edu.uco.arquisw.dominio.transversal.validador.ValidarTexto;
import lombok.Getter;

@Getter
public class Requisito {

    private String nombre;
    private String descripcion;
    private TipoRequisito tipoRequisito;

    private Requisito(String nombre, String descripcion, TipoRequisito tipoRequisito) {
        setNombre(nombre);
        setDescripcion(descripcion);
        this.tipoRequisito = tipoRequisito;
    }

    public static Requisito crear(String nombre, String descripcion, TipoRequisito tipoRequisito)
    {
        return new Requisito(nombre,descripcion,tipoRequisito);
    }

    public void setNombre(String nombre) {
        ValidarTexto.validarObligatorio(nombre, Mensajes.NOMBRE_REQUISITO_VACIO);
        ValidarTexto.validarPatronAlfanumericoEsValido(nombre, Mensajes.PATRON_NOMBRE_REQUISITO_NO_ES_VALIDO);
        this.nombre = nombre;
    }

    public void setDescripcion(String descripcion) {
        ValidarTexto.validarObligatorio(descripcion, Mensajes.DESCRIPCION_REQUISITO_VACIO);
        ValidarTexto.validarPatronAlfanumericoEsValido(descripcion, Mensajes.PATRON_DESCRIPCION_REQUISITO_NO_ES_VALIDO);
        this.descripcion = descripcion;
    }
}
