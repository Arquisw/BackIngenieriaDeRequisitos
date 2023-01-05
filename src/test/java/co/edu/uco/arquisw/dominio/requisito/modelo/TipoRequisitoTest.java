package co.edu.uco.arquisw.dominio.requisito.modelo;

import co.edu.uco.arquisw.dominio.transversal.excepciones.PatronExcepcion;
import co.edu.uco.arquisw.dominio.transversal.excepciones.ValorObligatorioExcepcion;
import co.edu.uco.arquisw.dominio.transversal.utilitario.Mensajes;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class TipoRequisitoTest {
    @Test
    void  validarCreacionEtapaExitosa()
    {
        String nombre =  "ingenieria de requisitos";

        TipoRequisito tipoRequisito = TipoRequisito.crear(nombre);

        Assertions.assertEquals(nombre,tipoRequisito.getNombre());
    }
    @Test
    void validarCamposFaltantes()
    {
        Assertions.assertEquals(Mensajes.NOMBRE_TIPO_REQUISITO_VACIO,Assertions.assertThrows(ValorObligatorioExcepcion.class,() ->
                TipoRequisito.crear("")).getMessage());

    }
    @Test
    void validarPatronesIncorrecto()
    {
        Assertions.assertEquals(Mensajes.PATRON_NOMBRE_TIPOREQUISITO_NO_ES_VALIDO,Assertions.assertThrows(PatronExcepcion.class,() ->
                TipoRequisito.crear("@-12+*_-°")).getMessage());
    }
}