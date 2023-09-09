package co.edu.uco.arquisw.dominio.etapa.modelo;

import co.edu.uco.arquisw.dominio.fase.modelo.Etapa;
import co.edu.uco.arquisw.dominio.transversal.excepciones.PatronExcepcion;
import co.edu.uco.arquisw.dominio.transversal.excepciones.ValorObligatorioExcepcion;
import co.edu.uco.arquisw.dominio.transversal.utilitario.Mensajes;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class EtapaTest {
    @Test
    void validarCreacionEtapaExitosa() {
        String nombre = "etapa uno";
        String descripcion = "primera etapa";
        boolean completada = true;

        Etapa etapa = Etapa.crear(nombre, descripcion, completada);

        Assertions.assertEquals(nombre, etapa.getNombre());
        Assertions.assertEquals(descripcion, etapa.getDescripcion());

    }

    @Test
    void validarCamposFaltantes() {
        Assertions.assertEquals(Mensajes.NOMBRE_ETAPA_VACIO, Assertions.assertThrows(ValorObligatorioExcepcion.class, () ->
                Etapa.crear("", "descripcion", true)).getMessage());

        Assertions.assertEquals(Mensajes.DESCRIPCION_ETAPA_VACIO, Assertions.assertThrows(ValorObligatorioExcepcion.class, () ->
                Etapa.crear("nombre", "", true)).getMessage());

    }

    @Test
    void validarPatronesIncorrecto() {
        Assertions.assertEquals(Mensajes.PATRON_NOMBRE_ETAPA_NO_ES_VALIDO, Assertions.assertThrows(PatronExcepcion.class, () ->
                Etapa.crear("@-12+*_-°", "descripcion", true)).getMessage());

        Assertions.assertEquals(Mensajes.PATRON_DESCRIPCION_ETAPA_NO_ES_VALIDO, Assertions.assertThrows(PatronExcepcion.class, () ->
                Etapa.crear("nombre", "@-12+*_-°", true)).getMessage());
    }
}
