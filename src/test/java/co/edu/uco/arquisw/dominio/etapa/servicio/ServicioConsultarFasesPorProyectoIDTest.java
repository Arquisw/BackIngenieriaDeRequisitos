package co.edu.uco.arquisw.dominio.etapa.servicio;

import co.edu.uco.arquisw.dominio.fase.dto.FaseDTO;
import co.edu.uco.arquisw.dominio.fase.puerto.consulta.FaseRepositorioConsulta;
import co.edu.uco.arquisw.dominio.fase.servicio.ServicioConsultarFasesPorProyectoID;
import co.edu.uco.arquisw.dominio.transversal.utilitario.Mensajes;
import co.edu.uco.arquisw.dominio.transversal.utilitario.NumeroConstante;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

class ServicioConsultarFasesPorProyectoIDTest {
    @Test
    void validarServicioConsultarFasesPorProyectoIDExitosa() {
        var faseDTO = new FaseDTO();
        List<FaseDTO> faseDTOS = List.of(faseDTO);

        var faseRepositorioConsulta = Mockito.mock(FaseRepositorioConsulta.class);

        var servicio = new ServicioConsultarFasesPorProyectoID(faseRepositorioConsulta);

        Mockito.when(faseRepositorioConsulta.consultarFasesPorProyectoID(Mockito.anyLong())).thenReturn(faseDTOS);

        var faseConsultada = servicio.ejecutar(NumeroConstante.UNO);

        Mockito.verify(faseRepositorioConsulta, Mockito.times(2)).consultarFasesPorProyectoID(NumeroConstante.UNO);

        Assertions.assertEquals(faseDTOS, faseConsultada);
    }

    @Test
    void consultaPorIdFallida() {
        var faseRepositorioConsulta = Mockito.mock(FaseRepositorioConsulta.class);

        var servicio = new ServicioConsultarFasesPorProyectoID(faseRepositorioConsulta);

        Mockito.when(faseRepositorioConsulta.consultarFasesPorProyectoID(Mockito.anyLong())).thenReturn(null);

        Assertions.assertEquals(Mensajes.obtenerNoExiteProyectoConId(NumeroConstante.UNO),
                Assertions.assertThrows(NullPointerException.class, () ->
                        servicio.ejecutar(NumeroConstante.UNO)
                ).getMessage());
    }
}
