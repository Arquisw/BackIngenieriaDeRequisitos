package co.edu.uco.arquisw.dominio.etapa.testdatabuilder;

import co.edu.uco.arquisw.dominio.fase.modelo.Etapa;
import co.edu.uco.arquisw.dominio.fase.modelo.Fase;

import java.util.List;

public class FaseTestDataBuilder {
    private String nombre;
    private String descripcion;
    private List<Etapa> etapas;

    public FaseTestDataBuilder() {
        this.nombre = "fase uno";
        this.descripcion = "primera fase";
        this.etapas = List.of(new EtapaTestDataBuilder().build());
    }

    public Fase build() {
        return Fase.crear(nombre, descripcion, etapas);
    }
}