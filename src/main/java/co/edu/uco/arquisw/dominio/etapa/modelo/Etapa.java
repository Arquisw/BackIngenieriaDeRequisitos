package co.edu.uco.arquisw.dominio.etapa.modelo;

import co.edu.uco.arquisw.dominio.version.modelo.Version;

import java.util.List;

public class Etapa {
    private String nombre;
    private String descripcion;
    private boolean completado;
    private List<Version> version;
}
