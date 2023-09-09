package co.edu.uco.arquisw.dominio.requisito.servicio;

import co.edu.uco.arquisw.dominio.fase.dto.EtapaDTO;
import co.edu.uco.arquisw.dominio.fase.puerto.consulta.FaseRepositorioConsulta;
import co.edu.uco.arquisw.dominio.requisito.dto.VersionDTO;
import co.edu.uco.arquisw.dominio.requisito.puerto.consulta.RequisitoRepositorioConsulta;
import co.edu.uco.arquisw.dominio.transversal.utilitario.Mensajes;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

class ServicioConsultarVersionPorEtapaTest {
    @Test
    void validarConsultarVersionPorEtapaExitosa() {
        var etapaDto = new EtapaDTO();
        var version = new VersionDTO();
        List<VersionDTO> versionDTOS = List.of(version);

        var requisitoRepositorioConsulta = Mockito.mock(RequisitoRepositorioConsulta.class);
        var faseRepositorioConsulta = Mockito.mock(FaseRepositorioConsulta.class);

        var servicio = new ServicioConsultarVersionesPorEtapaID(requisitoRepositorioConsulta, faseRepositorioConsulta);

        Mockito.when(faseRepositorioConsulta.consultarEtapaPorID(Mockito.anyLong())).thenReturn(etapaDto);
        Mockito.when(requisitoRepositorioConsulta.consultarVersionesPorEtapaID(Mockito.anyLong())).thenReturn(versionDTOS);

        var versionConsultada = servicio.ejecutar(1L);

        Mockito.verify(requisitoRepositorioConsulta, Mockito.times(1)).consultarVersionesPorEtapaID(1L);
        Assertions.assertEquals(versionDTOS, versionConsultada);
    }

    @Test
    void ConsultarVersionPorEtapaFallida() {

        var requisitoRepositorioConsulta = Mockito.mock(RequisitoRepositorioConsulta.class);
        var faseRepositorioConsulta = Mockito.mock(FaseRepositorioConsulta.class);

        var servicio = new ServicioConsultarVersionesPorEtapaID(requisitoRepositorioConsulta, faseRepositorioConsulta);

        Assertions.assertEquals(Mensajes.NO_EXISTE_PROYECTO_CON_EL_ID + 1L,
                Assertions.assertThrows(NullPointerException.class, () ->
                        servicio.ejecutar(1L)
                ).getMessage());
    }
}