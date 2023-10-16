package co.edu.uco.arquisw.dominio.requisito.modelo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class VersionTest {
    @Test
    void validarCreacionVersionExitosa() {
        boolean esFinal = true;
        boolean estaRechazada = false;

        Version version = Version.crear(esFinal, estaRechazada);

        Assertions.assertEquals(esFinal, version.isEsFinal());
    }
}