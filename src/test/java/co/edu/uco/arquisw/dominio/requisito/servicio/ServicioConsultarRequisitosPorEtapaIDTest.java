package co.edu.uco.arquisw.dominio.requisito.servicio;

import co.edu.uco.arquisw.dominio.fase.dto.EtapaDTO;
import co.edu.uco.arquisw.dominio.fase.puerto.FaseRepositorioConsulta;
import co.edu.uco.arquisw.dominio.requisito.dto.RequisitoDTO;
import co.edu.uco.arquisw.dominio.requisito.puerto.consulta.RequisitoRepositorioConsulta;
import co.edu.uco.arquisw.dominio.transversal.utilitario.Mensajes;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;
class ServicioConsultarRequisitosPorEtapaIDTest {
    @Test
    void validarServicioConsultarRequisitosPorEtapaIDExitosa()

    {
        var etapaDto = new EtapaDTO();
        var requisitosDto = new RequisitoDTO();
        List<RequisitoDTO> requisitoDTOS = List.of(requisitosDto);

        var requisitoRepositorioConsulta = Mockito.mock(RequisitoRepositorioConsulta.class);
        var faseRepositorioConsulta = Mockito.mock(FaseRepositorioConsulta.class);

        var servicio = new ServicioConsultarRequisitosPorEtapaID(requisitoRepositorioConsulta,faseRepositorioConsulta);

        Mockito.when(faseRepositorioConsulta.consultarEtapaPorID(Mockito.anyLong())).thenReturn(etapaDto);
        Mockito.when(requisitoRepositorioConsulta.consultarRequisitosPorEtapaID(Mockito.anyLong())).thenReturn(requisitoDTOS);

        var requisitoConsultado = servicio.ejecutar(1L);

        Mockito.verify(requisitoRepositorioConsulta, Mockito.times(1)).consultarRequisitosPorEtapaID(1L);
        Assertions.assertEquals(requisitoDTOS, requisitoConsultado);
    }

    @Test
    void consultaRequisitosPorEtapaIDFallida()
    {
        var requisitoRepositorioConsulta = Mockito.mock(RequisitoRepositorioConsulta.class);
        var faseRepositorioConsulta = Mockito.mock(FaseRepositorioConsulta.class);

        var servicio = new ServicioConsultarRequisitosPorEtapaID(requisitoRepositorioConsulta,faseRepositorioConsulta);

        Assertions.assertEquals(Mensajes.NO_EXISTE_ETAPA_CON_EL_ID + 1L,
                Assertions.assertThrows(NullPointerException.class, () ->
                        servicio.ejecutar(1L)
                ).getMessage());
    }
}
