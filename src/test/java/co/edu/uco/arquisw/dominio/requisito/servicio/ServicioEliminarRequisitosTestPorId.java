package co.edu.uco.arquisw.dominio.requisito.servicio;

import co.edu.uco.arquisw.dominio.requisito.dto.RequisitoDTO;
import co.edu.uco.arquisw.dominio.requisito.puerto.comando.RequisitoRepositorioComando;
import co.edu.uco.arquisw.dominio.requisito.puerto.consulta.RequisitoRepositorioConsulta;
import co.edu.uco.arquisw.dominio.transversal.utilitario.Mensajes;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class ServicioEliminarRequisitosTestPorId {
    @Test
    void validarServicioEliminacion() {
        var requisitoDTO = new RequisitoDTO();

        var requisitoRepositorioComando = Mockito.mock(RequisitoRepositorioComando.class);
        var requisitoRepositorioConsulta = Mockito.mock(RequisitoRepositorioConsulta.class);

        var servicio = new ServicioEliminarRequisitoPorID(requisitoRepositorioComando, requisitoRepositorioConsulta);

        Mockito.when(requisitoRepositorioConsulta.consultarRequisitoPorID(Mockito.anyLong())).thenReturn(requisitoDTO);

        var id = servicio.ejecutar(1L);

        Mockito.verify(requisitoRepositorioComando, Mockito.times(1)).eliminar(1L);

        Assertions.assertEquals(1L, id);
    }

    @Test
    void retornnaErrorNoExisteRequisito() {
        var requisitoRepositorioComando = Mockito.mock(RequisitoRepositorioComando.class);
        var requisitoRepositorioConsulta = Mockito.mock(RequisitoRepositorioConsulta.class);

        var servicio = new ServicioEliminarRequisitoPorID(requisitoRepositorioComando, requisitoRepositorioConsulta);

        Mockito.when(requisitoRepositorioConsulta.consultarRequisitoPorID(Mockito.anyLong())).thenReturn(null);

        Assertions.assertEquals(Mensajes.NO_EXISTE_REQUISITO_CON_EL_ID + 1L, Assertions.assertThrows(NullPointerException.class, () -> servicio.ejecutar(1L)).getMessage());
    }
}