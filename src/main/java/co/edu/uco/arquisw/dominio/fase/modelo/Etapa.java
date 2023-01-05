package co.edu.uco.arquisw.dominio.fase.modelo;

import co.edu.uco.arquisw.dominio.transversal.utilitario.Mensajes;
import co.edu.uco.arquisw.dominio.transversal.validador.ValidarTexto;
import lombok.Getter;

@Getter
public class Etapa {
    private String nombre;
    private String descripcion;
    private final boolean completada;

    private Etapa(String nombre, String descripcion, boolean completada) {
        setNombre(nombre);
        setDescripcion(descripcion);
        this.completada = completada;
    }

    public static Etapa crear(String nombre, String descripcion, boolean completada) {
        return new Etapa(nombre, descripcion, completada);
    }

    private void setNombre(String nombre) {
        ValidarTexto.validarObligatorio(nombre, Mensajes.NOMBRE_ETAPA_VACIO);
        ValidarTexto.validarPatronAlfanumericoEsValido(nombre, Mensajes.PATRON_NOMBRE_ETAPA_NO_ES_VALIDO);

        this.nombre = nombre;
    }

    private void setDescripcion(String descripcion) {
        ValidarTexto.validarObligatorio(descripcion, Mensajes.DESCRIPCION_ETAPA_VACIO);
        ValidarTexto.validarPatronAlfanumericoEsValido(descripcion, Mensajes.PATRON_DESCRIPCION_ETAPA_NO_ES_VALIDO);

        this.descripcion = descripcion;
    }
}