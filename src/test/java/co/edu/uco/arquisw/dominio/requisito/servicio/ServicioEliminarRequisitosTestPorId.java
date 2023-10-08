package co.edu.uco.arquisw.dominio.requisito.servicio;

import co.edu.uco.arquisw.dominio.requisito.dto.RequisitoDTO;
import co.edu.uco.arquisw.dominio.requisito.puerto.comando.RequisitoRepositorioComando;
import co.edu.uco.arquisw.dominio.requisito.puerto.consulta.RequisitoRepositorioConsulta;
import co.edu.uco.arquisw.dominio.transversal.utilitario.Mensajes;
import co.edu.uco.arquisw.dominio.transversal.utilitario.NumeroConstante;
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

        var id = servicio.ejecutar(NumeroConstante.UNO);

        Mockito.verify(requisitoRepositorioComando, Mockito.times(1)).eliminar(NumeroConstante.UNO);

        Assertions.assertEquals(NumeroConstante.UNO, id);
    }

    @Test
    void retornnaErrorNoExisteRequisito() {
        var requisitoRepositorioComando = Mockito.mock(RequisitoRepositorioComando.class);
        var requisitoRepositorioConsulta = Mockito.mock(RequisitoRepositorioConsulta.class);

        var servicio = new ServicioEliminarRequisitoPorID(requisitoRepositorioComando, requisitoRepositorioConsulta);

        Mockito.when(requisitoRepositorioConsulta.consultarRequisitoPorID(Mockito.anyLong())).thenReturn(null);

        Assertions.assertEquals(Mensajes.obtenerNoExiteRequisitoConId(NumeroConstante.UNO), Assertions.assertThrows(NullPointerException.class, () -> servicio.ejecutar(NumeroConstante.UNO)).getMessage());
    }
}