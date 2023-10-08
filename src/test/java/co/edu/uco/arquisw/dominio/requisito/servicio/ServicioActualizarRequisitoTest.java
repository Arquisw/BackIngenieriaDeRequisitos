package co.edu.uco.arquisw.dominio.requisito.servicio;

import co.edu.uco.arquisw.dominio.requisito.dto.RequisitoDTO;
import co.edu.uco.arquisw.dominio.requisito.dto.VersionDTO;
import co.edu.uco.arquisw.dominio.requisito.modelo.Requisito;
import co.edu.uco.arquisw.dominio.requisito.puerto.comando.RequisitoRepositorioComando;
import co.edu.uco.arquisw.dominio.requisito.puerto.consulta.RequisitoRepositorioConsulta;
import co.edu.uco.arquisw.dominio.requisito.testdatabuilder.RequisitoTestDataBuilder;
import co.edu.uco.arquisw.dominio.transversal.utilitario.Mensajes;
import co.edu.uco.arquisw.dominio.transversal.utilitario.NumeroConstante;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class ServicioActualizarRequisitoTest {
    @Test
    void actualizacionExitosa() {
        var requisito = new RequisitoTestDataBuilder().build();
        var requisitoDto = new RequisitoDTO();
        var versionDto = new VersionDTO();
        requisitoDto.setVersionID(NumeroConstante.UNO);

        var requisitoRepositorioComando = Mockito.mock(RequisitoRepositorioComando.class);
        var requisitoRepositorioConsulta = Mockito.mock(RequisitoRepositorioConsulta.class);
        var servicio = new ServicioActualizarRequisito(requisitoRepositorioComando, requisitoRepositorioConsulta);

        Mockito.when(requisitoRepositorioComando.actualizar(Mockito.any(Requisito.class), Mockito.anyLong())).thenReturn(NumeroConstante.UNO);
        Mockito.when(requisitoRepositorioConsulta.consultarRequisitoPorID(Mockito.anyLong())).thenReturn(requisitoDto);
        Mockito.when(requisitoRepositorioConsulta.consultarVersionPorID(Mockito.anyLong())).thenReturn(versionDto);

        var id = servicio.ejecutar(requisito, NumeroConstante.UNO);

        Assertions.assertEquals(NumeroConstante.UNO, id);
    }

    @Test
    void actualizacionFallaNoExisteRequisito() {
        var requisito = new RequisitoTestDataBuilder().build();
        var requisitoDto = new RequisitoDTO();
        var versionDto = new VersionDTO();
        requisitoDto.setVersionID(NumeroConstante.UNO);

        var requisitoRepositorioComando = Mockito.mock(RequisitoRepositorioComando.class);
        var requisitoRepositorioConsulta = Mockito.mock(RequisitoRepositorioConsulta.class);
        var servicio = new ServicioActualizarRequisito(requisitoRepositorioComando, requisitoRepositorioConsulta);

        Mockito.when(requisitoRepositorioComando.actualizar(Mockito.any(Requisito.class), Mockito.anyLong())).thenReturn(NumeroConstante.UNO);
        Mockito.when(requisitoRepositorioConsulta.consultarVersionPorID(Mockito.anyLong())).thenReturn(versionDto);

        Assertions.assertEquals(Mensajes.obtenerNoExiteRequisitoConId(NumeroConstante.UNO), Assertions.assertThrows(NullPointerException.class, ()
                -> servicio.ejecutar(requisito, NumeroConstante.UNO)).getMessage());

    }
}