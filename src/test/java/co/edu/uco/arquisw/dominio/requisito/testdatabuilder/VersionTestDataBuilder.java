package co.edu.uco.arquisw.dominio.requisito.testdatabuilder;

import co.edu.uco.arquisw.dominio.requisito.modelo.Version;

import java.time.LocalDate;

public class VersionTestDataBuilder {
    private final boolean esFinal;
    public VersionTestDataBuilder() {
        this.esFinal = true;
    }

    public Version build()
    {
        return Version.crear(esFinal);
    }

}
