package co.edu.uco.arquisw.dominio.etapa.testdatabuilder;

import co.edu.uco.arquisw.dominio.fase.modelo.Etapa;

public class EtapaTestDataBuilder {
    private String nombre;
    private String descripcion;
    private final boolean completada ;

    public EtapaTestDataBuilder()
    {
        this.nombre = "etapa uno";
        this.descripcion = "primera etapa";
        this.completada = true;
    }
    public Etapa build()
    {
        return Etapa.crear(nombre,descripcion,completada);
    }
}
