package co.edu.uco.arquisw.dominio.fase.modelo;

import co.edu.uco.arquisw.dominio.transversal.utilitario.Mensajes;
import co.edu.uco.arquisw.dominio.transversal.validador.ValidarTexto;
import lombok.Getter;
import java.util.List;

@Getter
public class Etapa {
    private String nombre;
    private String descripcion;
    private final boolean completada;
    private final List<Version> versiones;

    private Etapa(String nombre, String descripcion, boolean completada, List<Version> versiones) {
        setNombre(nombre);
        setDescripcion(descripcion);
        this.completada = completada;
        this.versiones = versiones;
    }

    public static Etapa crear(String nombre, String descripcion, boolean completada, List<Version> version) {
        return new Etapa(nombre, descripcion, completada, version);
    }

    public void setNombre(String nombre) {
        ValidarTexto.validarObligatorio(nombre, Mensajes.NOMBRE_ETAPA_VACIO);
        ValidarTexto.validarPatronAlfanumericoEsValido(nombre, Mensajes.PATRON_NOMBRE_ETAPA_NO_ES_VALIDO);

        this.nombre = nombre;
    }

    public void setDescripcion(String descripcion) {
        ValidarTexto.validarObligatorio(descripcion, Mensajes.DESCRIPCION_ETAPA_VACIO);
        ValidarTexto.validarPatronAlfanumericoEsValido(descripcion, Mensajes.PATRON_DESCRIPCION_ETAPA_NO_ES_VALIDO);

        this.descripcion = descripcion;
    }
}