package co.edu.uco.arquisw.dominio.requisito.testdatabuilder;

import co.edu.uco.arquisw.dominio.requisito.modelo.Requisito;
import co.edu.uco.arquisw.dominio.requisito.modelo.TipoRequisito;

public class RequisitoTestDataBuilder {
    private String nombre;
    private String descripcion;
    private final TipoRequisito tipoRequisito;

    public RequisitoTestDataBuilder() {
        this.nombre = "Riquisito";
        this.descripcion = "descripcion";
        this.tipoRequisito = new TipoRequisitoTestDataBuilder().build();
    }
    public Requisito build()
    {
        return Requisito.crear(nombre,descripcion,tipoRequisito);
    }
}
