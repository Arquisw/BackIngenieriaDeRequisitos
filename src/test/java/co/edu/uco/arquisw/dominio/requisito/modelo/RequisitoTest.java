package co.edu.uco.arquisw.dominio.requisito.modelo;


import co.edu.uco.arquisw.dominio.transversal.excepciones.PatronExcepcion;
import co.edu.uco.arquisw.dominio.transversal.excepciones.ValorObligatorioExcepcion;
import co.edu.uco.arquisw.dominio.transversal.utilitario.Mensajes;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class RequisitoTest {
    @Test
    void  validarCreacionEtapaExitosa()
    {
        String nombre =  "requisito ";
        String descripcion = "requisito ";
        TipoRequisito tipoRequisito = TipoRequisito.crear("ingenieria de requisitos");

        Requisito requisito = Requisito.crear(nombre, descripcion,tipoRequisito);

        Assertions.assertEquals(nombre,requisito.getNombre());
        Assertions.assertEquals(descripcion,requisito.getDescripcion());
        Assertions.assertEquals(tipoRequisito.getNombre(),requisito.getTipoRequisito().getNombre());
    }
    @Test
    void validarCamposFaltantes()
    {
        TipoRequisito tipoRequisito = TipoRequisito.crear("ingenieria de requisitos");

        Assertions.assertEquals(Mensajes.NOMBRE_REQUISITO_VACIO,Assertions.assertThrows(ValorObligatorioExcepcion.class,() ->
                Requisito.crear("","descripcion", tipoRequisito)).getMessage());

        Assertions.assertEquals(Mensajes.DESCRIPCION_REQUISITO_VACIO,Assertions.assertThrows(ValorObligatorioExcepcion.class,() ->
                Requisito.crear("nombre","", tipoRequisito)).getMessage());
    }
    @Test
    void validarPatronesIncorrecto()
    {
        TipoRequisito tipoRequisito = TipoRequisito.crear("ingenieria de requisitos");

        Assertions.assertEquals(Mensajes.PATRON_NOMBRE_REQUISITO_NO_ES_VALIDO,Assertions.assertThrows(PatronExcepcion.class,() ->
                Requisito.crear("@-12+*_-°","descripcion", tipoRequisito)).getMessage());

        Assertions.assertEquals(Mensajes.PATRON_DESCRIPCION_REQUISITO_NO_ES_VALIDO,Assertions.assertThrows(PatronExcepcion.class,() ->
                Requisito.crear("nombre","@-12+*_-°", tipoRequisito)).getMessage());
    }
}
