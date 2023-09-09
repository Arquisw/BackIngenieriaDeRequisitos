package co.edu.uco.arquisw.dominio.fase.modelo;

import co.edu.uco.arquisw.dominio.transversal.utilitario.Mensajes;
import co.edu.uco.arquisw.dominio.transversal.validador.ValidarTexto;
import lombok.Getter;

import java.util.List;

@Getter
public class Fase {
    private String nombre;
    private String descripcion;
    private final List<Etapa> etapas;

    private Fase(String nombre, String descripcion, List<Etapa> etapas) {
        setNombre(nombre);
        setDescripcion(descripcion);
        this.etapas = etapas;
    }

    public static Fase crear(String nombre, String descripcion, List<Etapa> etapas) {
        return new Fase(nombre, descripcion, etapas);
    }

    private void setNombre(String nombre) {
        ValidarTexto.validarObligatorio(nombre, Mensajes.NOMBRE_FASE_VACIO);
        ValidarTexto.validarPatronAlfanumericoEsValido(nombre, Mensajes.PATRON_NOMBRE_FASE_NO_ES_VALIDO);

        this.nombre = nombre;
    }

    private void setDescripcion(String descripcion) {
        ValidarTexto.validarObligatorio(descripcion, Mensajes.DESCRIPCION_FASE_VACIO);
        ValidarTexto.validarPatronAlfanumericoEsValido(descripcion, Mensajes.PATRON_DESCRIPCION_FASE_NO_ES_VALIDO);

        this.descripcion = descripcion;
    }
}