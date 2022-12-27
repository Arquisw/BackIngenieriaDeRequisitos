package co.edu.uco.arquisw.dominio.fase.modelo;

import co.edu.uco.arquisw.dominio.etapa.modelo.Etapa;
import co.edu.uco.arquisw.dominio.transversal.utilitario.Mensajes;
import co.edu.uco.arquisw.dominio.transversal.validador.ValidarTexto;
import lombok.Getter;

import java.util.List;
@Getter
public class Fase {
    private String nombre;
    private String descripcion;
    private boolean completado;
    private List<Etapa> etapa;

    private Fase(String nombre, String descripcion, boolean completado, List<Etapa> etapa) {
        setNombre(nombre);
        setDescripcion(descripcion);
        setCompletado(completado);
        setEtapa(etapa);
        this.etapa = etapa;
    }

    public static Fase crear(String nombre, String descripcion, boolean completado, List<Etapa> etapa)
    {
        return new Fase(nombre,descripcion,completado,etapa);
    }

    public void setNombre(String nombre) {
        ValidarTexto.validarObligatorio(nombre, Mensajes.NOMBRE_FASE_VACIO);
        ValidarTexto.validarPatronAlfanumericoEsValido(nombre, Mensajes.PATRON_NOMBRE_FASE_NO_ES_VALIDO);
        this.nombre = nombre;
    }

    public void setDescripcion(String descripcion) {
        ValidarTexto.validarObligatorio(descripcion, Mensajes.DESCRIPCION_FASE_VACIO);
        ValidarTexto.validarPatronAlfanumericoEsValido(descripcion, Mensajes.PATRON_DESCRIPCION_FASE_NO_ES_VALIDO);
        this.descripcion = descripcion;
    }

    public void setCompletado(boolean completado) {
        this.completado = completado;
    }

    public void setEtapa(List<Etapa> etapa) {
        this.etapa = etapa;
    }
}
