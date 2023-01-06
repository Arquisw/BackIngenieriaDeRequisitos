package co.edu.uco.arquisw.dominio.etapa.servicio;


import co.edu.uco.arquisw.dominio.fase.dto.EtapaDTO;
import co.edu.uco.arquisw.dominio.fase.dto.FaseDTO;
import co.edu.uco.arquisw.dominio.fase.modelo.Fase;
import co.edu.uco.arquisw.dominio.fase.puerto.FaseRepositorioComando;
import co.edu.uco.arquisw.dominio.fase.puerto.FaseRepositorioConsulta;
import co.edu.uco.arquisw.dominio.fase.servicio.ServicioAprobarEtapa;
import co.edu.uco.arquisw.dominio.transversal.excepciones.AccionExcepcion;
import co.edu.uco.arquisw.dominio.transversal.utilitario.Mensajes;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.mockito.Mockito;

public class ServicioAprobarEtapaTest {
    @Test
    void aprobarExitoso()
    {
        var EtapaDto= new EtapaDTO();
        var faseDto = new FaseDTO();
        EtapaDto.setNombre("NOMBRE");
        EtapaDto.setDescripcion("descripcion");

        var faseRepositorioComando = Mockito.mock(FaseRepositorioComando.class);
        var faseRepositorioConsulta= Mockito.mock(FaseRepositorioConsulta.class);

        var servicio = new ServicioAprobarEtapa(faseRepositorioComando,faseRepositorioConsulta);

        Mockito.when(faseRepositorioComando.guardar(Mockito.any(Fase.class),Mockito.anyLong())).thenReturn(1L);
        Mockito.when(faseRepositorioConsulta.consultarEtapaPorID(Mockito.anyLong())).thenReturn(EtapaDto);
        Mockito.when(faseRepositorioConsulta.consultarFasePorEtapaID(Mockito.anyLong())).thenReturn(faseDto);

        var id = servicio.ejecutar(1L);

        Assertions.assertEquals(0,id);
    }

    @Test
    void aprobarFallaNoExisteEtapa()
    {
        var EtapaDto= new EtapaDTO();
        var faseDto = new FaseDTO();
        EtapaDto.setNombre("NOMBRE");
        EtapaDto.setDescripcion("descripcion");

        var faseRepositorioComando = Mockito.mock(FaseRepositorioComando.class);
        var faseRepositorioConsulta= Mockito.mock(FaseRepositorioConsulta.class);

        var servicio = new ServicioAprobarEtapa(faseRepositorioComando,faseRepositorioConsulta);

        Mockito.when(faseRepositorioComando.guardar(Mockito.any(Fase.class),Mockito.anyLong())).thenReturn(1L);
        Mockito.when(faseRepositorioConsulta.consultarFasePorEtapaID(Mockito.anyLong())).thenReturn(faseDto);

        Assertions.assertEquals(Mensajes.NO_EXISTE_ETAPA_CON_EL_ID + 1, Assertions.assertThrows(NullPointerException.class, ()
                -> servicio.ejecutar(1L)).getMessage());
    }
    @Test
    void aprobarFallaEtapaCompleta()
    {
        var EtapaDto= new EtapaDTO();
        var faseDto = new FaseDTO();
        EtapaDto.setId(1L);
        EtapaDto.setNombre("NOMBRE");
        EtapaDto.setDescripcion("descripcion");
        EtapaDto.setCompletada(true);

        var faseRepositorioComando = Mockito.mock(FaseRepositorioComando.class);
        var faseRepositorioConsulta= Mockito.mock(FaseRepositorioConsulta.class);

        var servicio = new ServicioAprobarEtapa(faseRepositorioComando,faseRepositorioConsulta);

        Mockito.when(faseRepositorioComando.guardar(Mockito.any(Fase.class),Mockito.anyLong())).thenReturn(1L);
        Mockito.when(faseRepositorioConsulta.consultarEtapaPorID(Mockito.anyLong())).thenReturn(EtapaDto);
        Mockito.when(faseRepositorioConsulta.consultarFasePorEtapaID(Mockito.anyLong())).thenReturn(faseDto);

        Assertions.assertEquals(Mensajes.ETAPA_CON_ID + 1 + Mensajes.ETAPA_YA_ESTA_COMPLETADA , Assertions.assertThrows(AccionExcepcion.class, ()
                -> servicio.ejecutar(1L)).getMessage());
    }
}
