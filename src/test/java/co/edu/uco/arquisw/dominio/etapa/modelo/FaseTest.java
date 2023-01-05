package co.edu.uco.arquisw.dominio.etapa.modelo;

import co.edu.uco.arquisw.dominio.fase.modelo.Etapa;
import co.edu.uco.arquisw.dominio.fase.modelo.Fase;
import co.edu.uco.arquisw.dominio.transversal.excepciones.PatronExcepcion;
import co.edu.uco.arquisw.dominio.transversal.excepciones.ValorObligatorioExcepcion;
import co.edu.uco.arquisw.dominio.transversal.utilitario.Mensajes;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class FaseTest {
    @Test
    void  validarCreacionFaseExitosa()
    {
        Etapa etapa = Etapa.crear("etapa","descripcion", true);
        String nombre =  "fase uno";
        String descripcion = "primera fase";
        List<Etapa> etapas = new ArrayList<>() ;
        etapas.add(etapa);

        Fase fase = Fase.crear(nombre, descripcion,etapas);

        Assertions.assertEquals(nombre,fase.getNombre());
        Assertions.assertEquals(descripcion,fase.getDescripcion());
        Assertions.assertEquals(etapas,fase.getEtapas());

    }
    @Test
    void validarCamposFaltantes()
    {
        Etapa etapa = Etapa.crear("etapa","descripcion", true);
        List<Etapa> etapas = new ArrayList<>() ;
        etapas.add(etapa);

        Assertions.assertEquals(Mensajes.NOMBRE_FASE_VACIO,Assertions.assertThrows(ValorObligatorioExcepcion.class,() ->
                Fase.crear("","descripcion", etapas)).getMessage());

        Assertions.assertEquals(Mensajes.DESCRIPCION_FASE_VACIO,Assertions.assertThrows(ValorObligatorioExcepcion.class,() ->
                Fase.crear("nombre","", etapas)).getMessage());

    }
    @Test
    void validarPatronesIncorrecto()
    {
        Etapa etapa = Etapa.crear("etapa","descripcion", true);
        List<Etapa> etapas = new ArrayList<>() ;
        etapas.add(etapa);

        Assertions.assertEquals(Mensajes.PATRON_NOMBRE_FASE_NO_ES_VALIDO,Assertions.assertThrows(PatronExcepcion.class,() ->
                Fase.crear("@-12+*_-°","descripcion", etapas)).getMessage());

        Assertions.assertEquals(Mensajes.PATRON_DESCRIPCION_FASE_NO_ES_VALIDO,Assertions.assertThrows(PatronExcepcion.class,() ->
                Fase.crear("nombre","@-12+*_-°", etapas)).getMessage());
    }
}
