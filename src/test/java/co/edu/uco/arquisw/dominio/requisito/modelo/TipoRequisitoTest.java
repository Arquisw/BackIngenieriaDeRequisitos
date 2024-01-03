package co.edu.uco.arquisw.dominio.requisito.modelo;

import co.edu.uco.arquisw.dominio.transversal.excepciones.LongitudExcepcion;
import co.edu.uco.arquisw.dominio.transversal.excepciones.PatronExcepcion;
import co.edu.uco.arquisw.dominio.transversal.excepciones.ValorObligatorioExcepcion;
import co.edu.uco.arquisw.dominio.transversal.utilitario.Mensajes;
import co.edu.uco.arquisw.dominio.transversal.utilitario.NumeroConstante;
import co.edu.uco.arquisw.dominio.transversal.utilitario.TextoConstante;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class TipoRequisitoTest {
    @Test
    void validarCreacionEtapaExitosa() {
        var nombre = TextoConstante.TIPO_REQUISITO_FUNCIONAL;

        var tipoRequisito = TipoRequisito.crear(nombre);

        assertNotNull(tipoRequisito);
        assertEquals(nombre, tipoRequisito.getNombre());
    }

    @Test
    void validarCamposFaltantes() {
        var nombre = TextoConstante.VACIO;

        assertEquals(Mensajes.NOMBRE_TIPO_REQUISITO_VACIO, Assertions.assertThrows(ValorObligatorioExcepcion.class, () ->
                TipoRequisito.crear(nombre)).getMessage());
    }

    @Test
    void validarPatronesIncorrecto() {
        var nombre = "@-12+*_-°";

        assertEquals(Mensajes.PATRON_NOMBRE_TIPO_REQUISITO_NO_ES_VALIDO, Assertions.assertThrows(PatronExcepcion.class, () ->
                TipoRequisito.crear(nombre)).getMessage());
    }

    @Test
    void validarLongitudInvalida() {
        var nombre = "NO Funcionalmente";

        assertEquals(Mensajes.obtenerNombreTipoRequisitoNoPuedeExcederLongitud(NumeroConstante.DOCE_ENTERO), Assertions.assertThrows(LongitudExcepcion.class, () ->
                TipoRequisito.crear(nombre)).getMessage());
    }
}
