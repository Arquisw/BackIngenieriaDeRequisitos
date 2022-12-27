package co.edu.uco.arquisw.dominio.fase.modelo;

import co.edu.uco.arquisw.dominio.transversal.excepciones.PatronExcepcion;
import co.edu.uco.arquisw.dominio.transversal.excepciones.ValorObligatorioExcepcion;
import co.edu.uco.arquisw.dominio.transversal.utilitario.Mensajes;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class FaseTest {
    @Test
    void  validarCreacionFaseExitosa()
    {
        String nombre = "Fase 1";
        String descripcion = "Fase 1";
        boolean completado = false;
        Fase fase = Fase.crear(nombre, descripcion,completado, null);

        Assertions.assertEquals(nombre,fase.getNombre());
        Assertions.assertEquals(descripcion,fase.getDescripcion());
        Assertions.assertEquals(completado,fase.isCompletado());
    }
    @Test
    void validarCamposFaltantes()
    {
        Assertions.assertEquals(Mensajes.NOMBRE_FASE_VACIO, Assertions.assertThrows(ValorObligatorioExcepcion.class,() ->
        Fase.crear("", "descripcion",false, null)).getMessage());

        Assertions.assertEquals(Mensajes.DESCRIPCION_FASE_VACIO, Assertions.assertThrows(ValorObligatorioExcepcion.class,() ->
                Fase.crear("Nombre", "",false, null)).getMessage());

    }
    @Test
    void validarPatronesIncorrecto()
    {
        Assertions.assertEquals(Mensajes.PATRON_NOMBRE_FASE_NO_ES_VALIDO, Assertions.assertThrows(PatronExcepcion.class,() ->
                Fase.crear("12asd-@!°^%", "descripcion",false, null)).getMessage());

        Assertions.assertEquals(Mensajes.PATRON_DESCRIPCION_FASE_NO_ES_VALIDO, Assertions.assertThrows(PatronExcepcion.class,() ->
                Fase.crear("Nombre", "123-@!°^^%",false, null)).getMessage());
    }
}
