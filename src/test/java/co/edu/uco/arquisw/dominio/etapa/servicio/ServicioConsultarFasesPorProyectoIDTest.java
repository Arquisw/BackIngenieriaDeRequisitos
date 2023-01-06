package co.edu.uco.arquisw.dominio.etapa.servicio;

import co.edu.uco.arquisw.dominio.fase.dto.FaseDTO;
import co.edu.uco.arquisw.dominio.fase.dto.ProyectoDTO;
import co.edu.uco.arquisw.dominio.fase.puerto.FaseRepositorioConsulta;
import co.edu.uco.arquisw.dominio.fase.servicio.ServicioConsultarFasesPorProyectoID;
import co.edu.uco.arquisw.dominio.transversal.utilitario.Mensajes;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

class ServicioConsultarFasesPorProyectoIDTest {
    @Test
    void validarServicioConsultarFasesPorProyectoIDExitosa()

    {
        var proyectoDto = new ProyectoDTO();
        var faseDTO = new FaseDTO();
        List<FaseDTO> faseDTOS = List.of(faseDTO);

        var faseRepositorioConsulta = Mockito.mock(FaseRepositorioConsulta.class);

        var servicio = new ServicioConsultarFasesPorProyectoID(faseRepositorioConsulta);

        Mockito.when(faseRepositorioConsulta.consultarFasesPorProyectoID(Mockito.anyLong())).thenReturn(faseDTOS);

        var faseConsultada = servicio.ejecutar(1L);

        Mockito.verify(faseRepositorioConsulta, Mockito.times(2)).consultarFasesPorProyectoID(1L);

        Assertions.assertEquals(faseDTOS, faseConsultada);
    }

    @Test
    void consultaPorIdFallida()
    {
        var faseRepositorioConsulta = Mockito.mock(FaseRepositorioConsulta.class);

        var servicio = new ServicioConsultarFasesPorProyectoID(faseRepositorioConsulta);

        Mockito.when(faseRepositorioConsulta.consultarFasesPorProyectoID(Mockito.anyLong())).thenReturn(null);

        Assertions.assertEquals(Mensajes.NO_EXISTE_PROYECTO_CON_EL_ID + 1L,
                Assertions.assertThrows(NullPointerException.class, () ->
                        servicio.ejecutar(1L)
                ).getMessage());
    }
}
