package co.edu.uco.arquisw.dominio.requisito.modelo;

import co.edu.uco.arquisw.dominio.etapa.modelo.Etapa;
import co.edu.uco.arquisw.dominio.transversal.excepciones.PatronExcepcion;
import co.edu.uco.arquisw.dominio.transversal.excepciones.ValorObligatorioExcepcion;
import co.edu.uco.arquisw.dominio.transversal.utilitario.Mensajes;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class RequisitoTest {
    @Test
    void  validarCreacionFaseExitosa()
    {
        String nombre = "Fase 1";
        String descripcion = "Fase 1";

        Requisito requisito = Requisito.crear(nombre, descripcion, null);

        Assertions.assertEquals(nombre,requisito.getNombre());
        Assertions.assertEquals(descripcion,requisito.getDescripcion());

    }
    @Test
    void validarCamposFaltantes()
    {
        Assertions.assertEquals(Mensajes.NOMBRE_REQUISITO_VACIO, Assertions.assertThrows(ValorObligatorioExcepcion.class,() ->
                Requisito.crear("", "descripcion",null)).getMessage());

        Assertions.assertEquals(Mensajes.DESCRIPCION_REQUISITO_VACIO, Assertions.assertThrows(ValorObligatorioExcepcion.class,() ->
                Requisito.crear("Nombre", "", null)).getMessage());

    }
    @Test
    void validarPatronesIncorrecto()
    {
        Assertions.assertEquals(Mensajes.PATRON_NOMBRE_REQUISITO_NO_ES_VALIDO, Assertions.assertThrows(PatronExcepcion.class,() ->
                Requisito.crear("12asd-@!°^%", "descripcion", null)).getMessage());

        Assertions.assertEquals(Mensajes.PATRON_DESCRIPCION_REQUISITO_NO_ES_VALIDO, Assertions.assertThrows(PatronExcepcion.class,() ->
                Requisito.crear("Nombre", "123-@!°^^%", null)).getMessage());
    }
}
