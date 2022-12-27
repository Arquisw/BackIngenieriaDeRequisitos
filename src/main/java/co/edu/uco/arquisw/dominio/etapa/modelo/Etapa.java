package co.edu.uco.arquisw.dominio.etapa.modelo;

import co.edu.uco.arquisw.dominio.transversal.utilitario.Mensajes;
import co.edu.uco.arquisw.dominio.transversal.validador.ValidarTexto;
import co.edu.uco.arquisw.dominio.version.modelo.Version;
import lombok.Getter;

import java.util.List;
@Getter
public class Etapa {
    private String nombre;
    private String descripcion;
    private boolean completado;
    private List<Version> version;

    private Etapa(String nombre, String descripcion, boolean completado, List<Version> version) {
        setNombre(nombre);
        setDescripcion(descripcion);
        setCompletado(completado);
        this.version = version;
    }

    public static Etapa crear(String nombre, String descripcion, boolean completado, List<Version> version)
    {
        return new Etapa(nombre,descripcion,completado,version);
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

    public void setCompletado(boolean completado) {
        this.completado = completado;
    }
}
