package co.edu.uco.arquisw.dominio.requisito.servicio;

import co.edu.uco.arquisw.dominio.fase.dto.EtapaDTO;
import co.edu.uco.arquisw.dominio.requisito.dto.VersionDTO;
import co.edu.uco.arquisw.dominio.requisito.modelo.Requisito;
import co.edu.uco.arquisw.dominio.requisito.puerto.comando.RequisitoRepositorioComando;
import co.edu.uco.arquisw.dominio.requisito.puerto.consulta.RequisitoRepositorioConsulta;
import co.edu.uco.arquisw.dominio.requisito.testdatabuilder.RequisitoTestDataBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

public class ServicioGuardarRequisitoTest {
    @Test
    void validarGuardarExitoso() {
        var requisito = new RequisitoTestDataBuilder().build();
        var versionDto = new VersionDTO();
        var EtapaDto = new EtapaDTO();
        List<VersionDTO> versionDTOS = List.of(versionDto);

        var requisitoRepositorioComando = Mockito.mock(RequisitoRepositorioComando.class);
        var requisitoRepositorioConsulta = Mockito.mock(RequisitoRepositorioConsulta.class);

        var servicio = new ServicioGuardarRequisito(requisitoRepositorioComando, requisitoRepositorioConsulta);

        Mockito.when(requisitoRepositorioConsulta.consultarVersionesPorEtapaID(Mockito.anyLong())).thenReturn(versionDTOS);
        Mockito.when(requisitoRepositorioConsulta.consultarVersionPorID(Mockito.anyLong())).thenReturn(versionDto);

        Mockito.when(requisitoRepositorioComando.guardar(Mockito.any(Requisito.class), Mockito.anyLong())).thenReturn(1L);

        var id = servicio.ejecutar(requisito, 1L);

        Assertions.assertEquals(1L, id);
    }
}