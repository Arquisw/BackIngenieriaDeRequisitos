package co.edu.uco.arquisw.dominio.etapa.modelo;

import co.edu.uco.arquisw.dominio.fase.modelo.Fase;
import co.edu.uco.arquisw.dominio.transversal.excepciones.PatronExcepcion;
import co.edu.uco.arquisw.dominio.transversal.excepciones.ValorObligatorioExcepcion;
import co.edu.uco.arquisw.dominio.transversal.utilitario.Mensajes;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class EtapaTest {

    @Test
    void  validarCreacionFaseExitosa()
    {
        String nombre = "Fase 1";
        String descripcion = "Fase 1";
        boolean completado = false;
        Etapa etapa = Etapa.crear(nombre, descripcion,completado, null);

        Assertions.assertEquals(nombre,etapa.getNombre());
        Assertions.assertEquals(descripcion,etapa.getDescripcion());
        Assertions.assertEquals(completado,etapa.isCompletado());
    }
    @Test
    void validarCamposFaltantes()
    {
        Assertions.assertEquals(Mensajes.NOMBRE_ETAPA_VACIO, Assertions.assertThrows(ValorObligatorioExcepcion.class,() ->
                Etapa.crear("", "descripcion",false, null)).getMessage());

        Assertions.assertEquals(Mensajes.DESCRIPCION_ETAPA_VACIO, Assertions.assertThrows(ValorObligatorioExcepcion.class,() ->
                Etapa.crear("Nombre", "",false, null)).getMessage());

    }
    @Test
    void validarPatronesIncorrecto()
    {
        Assertions.assertEquals(Mensajes.PATRON_NOMBRE_ETAPA_NO_ES_VALIDO, Assertions.assertThrows(PatronExcepcion.class,() ->
                Etapa.crear("12asd-@!°^%", "descripcion",false, null)).getMessage());

        Assertions.assertEquals(Mensajes.PATRON_DESCRIPCION_ETAPA_NO_ES_VALIDO, Assertions.assertThrows(PatronExcepcion.class,() ->
                Etapa.crear("Nombre", "123-@!°^^%",false, null)).getMessage());
    }
}
