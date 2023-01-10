package co.edu.uco.arquisw.dominio.requisito.servicio;

import co.edu.uco.arquisw.dominio.requisito.dto.RequisitoDTO;
import co.edu.uco.arquisw.dominio.requisito.puerto.consulta.RequisitoRepositorioConsulta;
import co.edu.uco.arquisw.dominio.transversal.utilitario.Mensajes;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
class ServicioConsultarRequisitoPorIDTest {
    @Test
    void validarServicioConsultarRequisitoPorIDExitosa()
    {
        var requisitoDto = new RequisitoDTO();
        var requisitoRepositorioConsulta = Mockito.mock(RequisitoRepositorioConsulta.class);
        var servicio = new ServicioConsultarRequisitoPorID(requisitoRepositorioConsulta);

        Mockito.when(requisitoRepositorioConsulta.consultarRequisitoPorID(Mockito.anyLong())).thenReturn(requisitoDto);

        var requisitoConsultada = servicio.ejecutar(1L);

        Mockito.verify(requisitoRepositorioConsulta, Mockito.times(2)).consultarRequisitoPorID(1L);

        Assertions.assertEquals(requisitoDto, requisitoConsultada);
    }

    @Test
    void ConsultarRequisitoPorIDFallida()
    {
        var requisitoRepositorioConsulta = Mockito.mock(RequisitoRepositorioConsulta.class);

        var servicio = new ServicioConsultarRequisitoPorID(requisitoRepositorioConsulta);

        Assertions.assertEquals(Mensajes.NO_EXISTE_REQUISITO_CON_EL_ID + 1L,
                Assertions.assertThrows(NullPointerException.class, () ->
                        servicio.ejecutar(1L)
                ).getMessage());
    }
}
