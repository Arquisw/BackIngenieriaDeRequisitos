package co.edu.uco.arquisw.dominio.requisito.testdatabuilder;


import co.edu.uco.arquisw.dominio.requisito.modelo.TipoRequisito;

public class TipoRequisitoTestDataBuilder {
    private String nombre;

    public TipoRequisitoTestDataBuilder() {
        this.nombre = "NO Funcional";
    }

    public TipoRequisito build() {
        return TipoRequisito.crear(nombre);
    }
}