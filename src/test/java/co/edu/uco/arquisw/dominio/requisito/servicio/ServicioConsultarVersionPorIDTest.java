package co.edu.uco.arquisw.dominio.requisito.servicio;


import co.edu.uco.arquisw.dominio.requisito.dto.VersionDTO;
import co.edu.uco.arquisw.dominio.requisito.puerto.consulta.RequisitoRepositorioConsulta;
import co.edu.uco.arquisw.dominio.transversal.utilitario.Mensajes;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class ServicioConsultarVersionPorIDTest {
    @Test
    void validarServicioConsultarRequisitoPorIDExitosa()

    {
        var versionDto = new VersionDTO();
        var requisitoRepositorioConsulta = Mockito.mock(RequisitoRepositorioConsulta.class);
        var servicio = new ServicioConsultarVersionPorID(requisitoRepositorioConsulta);

        Mockito.when(requisitoRepositorioConsulta.consultarVersionPorID(Mockito.anyLong())).thenReturn(versionDto);

        var requisitoConsultada = servicio.ejecutar(1L);

        Mockito.verify(requisitoRepositorioConsulta, Mockito.times(2)).consultarVersionPorID(1L);

        Assertions.assertEquals(versionDto, requisitoConsultada);
    }

    @Test
    void ConsultarRequisitoPorIDFallida()
    {
        var requisitoRepositorioConsulta = Mockito.mock(RequisitoRepositorioConsulta.class);

        var servicio = new ServicioConsultarVersionPorID(requisitoRepositorioConsulta);
        Assertions.assertEquals(Mensajes.NO_EXISTE_VERSION_CON_EL_ID + 1L,
                Assertions.assertThrows(NullPointerException.class, () ->
                        servicio.ejecutar(1L)
                ).getMessage());
    }
}
