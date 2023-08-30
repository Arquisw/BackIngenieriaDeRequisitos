package co.edu.uco.arquisw.dominio.etapa.servicio;

import co.edu.uco.arquisw.dominio.fase.dto.FaseDTO;
import co.edu.uco.arquisw.dominio.fase.puerto.consulta.FaseRepositorioConsulta;
import co.edu.uco.arquisw.dominio.fase.servicio.ServicioConsultarEtapaPorID;
import co.edu.uco.arquisw.dominio.transversal.utilitario.Mensajes;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class ServicioConsultarFasePorIdTest {
    @Test
    void validarConsultarFasePorIdExitosa()
    {
        var faseDTO = new FaseDTO();

        var faseRepositorioConsulta = Mockito.mock(FaseRepositorioConsulta.class);

        var servicio = new ServicioConsultarEtapaPorID(faseRepositorioConsulta);

        Mockito.when(faseRepositorioConsulta.consultarFasePorID(Mockito.anyLong())).thenReturn(faseDTO);

        var faseConsultada = servicio.ejecutar(1L);

        Mockito.verify(faseRepositorioConsulta, Mockito.times(2)).consultarFasePorID(1L);

        Assertions.assertEquals(faseDTO, faseConsultada);
    }

    @Test
    void consultaPorIdFallida()
    {
        var faseRepositorioConsulta = Mockito.mock(FaseRepositorioConsulta.class);

        var servicio = new ServicioConsultarEtapaPorID(faseRepositorioConsulta);

        Mockito.when(faseRepositorioConsulta.consultarFasePorID(Mockito.anyLong())).thenReturn(null);

        Assertions.assertEquals(Mensajes.NO_EXISTE_FASE_CON_EL_ID + 1L,
                Assertions.assertThrows(NullPointerException.class, () ->
                        servicio.ejecutar(1L)
                ).getMessage());
    }
}
