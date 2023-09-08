package co.edu.uco.arquisw.dominio.etapa.servicio;

import co.edu.uco.arquisw.dominio.fase.dto.proyecto.ProyectoDTO;
import co.edu.uco.arquisw.dominio.fase.modelo.Fase;
import co.edu.uco.arquisw.dominio.fase.puerto.comando.FaseRepositorioComando;
import co.edu.uco.arquisw.dominio.fase.puerto.consulta.FaseRepositorioConsulta;
import co.edu.uco.arquisw.dominio.fase.puerto.consulta.ProyectoRepositorioConsulta;
import co.edu.uco.arquisw.dominio.fase.servicio.ServicioGuardarFase;
import co.edu.uco.arquisw.dominio.transversal.utilitario.Mensajes;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;


class ServicioGuardarFaseTest {
    @Test
    void guardarExitoso()
    {
        var ProyectoDto = new ProyectoDTO();

        var faseRepositorioComando = Mockito.mock(FaseRepositorioComando.class);
        var faseRepositorioConsulta = Mockito.mock(FaseRepositorioConsulta.class);
        var proyectoRepositorioConsulta = Mockito.mock(ProyectoRepositorioConsulta.class);

        var servicio = new ServicioGuardarFase(faseRepositorioComando,faseRepositorioConsulta, proyectoRepositorioConsulta);

        Mockito.when(proyectoRepositorioConsulta.consultarProyectoPorID(Mockito.anyLong())).thenReturn(ProyectoDto);
        Mockito.when(faseRepositorioComando.guardar(Mockito.any(Fase.class), Mockito.anyLong())).thenReturn(1L);

        var id = servicio.ejecutar(1l);

        Assertions.assertEquals(1L,id);
    }
    @Test
    void deberiaValidarLaExistenciaPreviaDelProyecto() {

        var faseRepositorioComando = Mockito.mock(FaseRepositorioComando.class);
        var faseRepositorioConsulta= Mockito.mock(FaseRepositorioConsulta.class);
        var proyectoRepositorioConsulta = Mockito.mock(ProyectoRepositorioConsulta.class);

        var servicio = new ServicioGuardarFase(faseRepositorioComando,faseRepositorioConsulta, proyectoRepositorioConsulta);

        Mockito.when(proyectoRepositorioConsulta.consultarProyectoPorID(Mockito.anyLong())).thenReturn(null);
        Mockito.when(faseRepositorioComando.guardar(Mockito.any(Fase.class), Mockito.anyLong())).thenReturn(1L);

        Assertions.assertEquals(Mensajes.NO_EXISTE_PROYECTO_CON_EL_ID + 1, Assertions.assertThrows(NullPointerException.class, ()
                -> servicio.ejecutar(1L)).getMessage());
    }
}
