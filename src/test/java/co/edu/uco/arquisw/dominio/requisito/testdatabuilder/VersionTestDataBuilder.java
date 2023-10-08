package co.edu.uco.arquisw.dominio.requisito.testdatabuilder;

import co.edu.uco.arquisw.dominio.requisito.modelo.Version;

public class VersionTestDataBuilder {
    private final boolean esFinal;
    private final boolean estaRechazada;

    public VersionTestDataBuilder() {
        this.esFinal = true;
        this.estaRechazada = false;
    }

    public Version build() {
        return Version.crear(esFinal, estaRechazada);
    }
}