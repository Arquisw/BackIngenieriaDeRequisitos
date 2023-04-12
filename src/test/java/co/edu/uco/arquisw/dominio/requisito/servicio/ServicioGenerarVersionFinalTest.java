package co.edu.uco.arquisw.dominio.requisito.servicio;

import co.edu.uco.arquisw.dominio.fase.dto.EtapaDTO;
import co.edu.uco.arquisw.dominio.fase.puerto.consulta.FaseRepositorioConsulta;
import co.edu.uco.arquisw.dominio.requisito.dto.VersionDTO;
import co.edu.uco.arquisw.dominio.requisito.modelo.Version;
import co.edu.uco.arquisw.dominio.requisito.puerto.comando.RequisitoRepositorioComando;
import co.edu.uco.arquisw.dominio.requisito.puerto.consulta.RequisitoRepositorioConsulta;
import co.edu.uco.arquisw.dominio.transversal.excepciones.AccionExcepcion;
import co.edu.uco.arquisw.dominio.transversal.utilitario.Mensajes;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

public class ServicioGenerarVersionFinalTest {
    @Test
    void generarVersionFinalExitosa()
    {
        var versionDto = new VersionDTO();
        var EtapaDto = new EtapaDTO();
        List<VersionDTO> versionDTOS = List.of(versionDto);

        var requisitoRepositorioComando = Mockito.mock(RequisitoRepositorioComando.class);
        var requisitoRepositorioConsulta= Mockito.mock(RequisitoRepositorioConsulta.class);
        var faseRepositorioConsulta = Mockito.mock(FaseRepositorioConsulta.class);
        var ServicioObtenerVersionFinal= Mockito.mock(ServicioObtenerVersionFinal.class);

        var servicio = new ServicioGenerarVersionFinal(requisitoRepositorioComando,requisitoRepositorioConsulta,faseRepositorioConsulta,ServicioObtenerVersionFinal);

        Mockito.when(requisitoRepositorioComando.actualizarVersion(Mockito.any(Version.class),Mockito.anyLong())).thenReturn(1L);
        Mockito.when(faseRepositorioConsulta.consultarEtapaPorID(Mockito.anyLong())).thenReturn(EtapaDto);
        Mockito.when(requisitoRepositorioConsulta.consultarVersionesPorEtapaID(Mockito.anyLong())).thenReturn(versionDTOS);
        Mockito.when(ServicioObtenerVersionFinal.ejecutar(Mockito.anyList())).thenReturn(versionDto);
        Mockito.when(requisitoRepositorioConsulta.consultarVersionPorID(Mockito.anyLong())).thenReturn(versionDto);

        var id = servicio.ejecutar(1L);

        Assertions.assertEquals(0,id);
    }
    @Test
    void FallaNoExisteEtapa()
    {
        var versionDto = new VersionDTO();
        List<VersionDTO> versionDTOS = List.of(versionDto);

        var requisitoRepositorioComando = Mockito.mock(RequisitoRepositorioComando.class);
        var requisitoRepositorioConsulta= Mockito.mock(RequisitoRepositorioConsulta.class);
        var faseRepositorioConsulta = Mockito.mock(FaseRepositorioConsulta.class);
        var ServicioObtenerVersionFinal= Mockito.mock(ServicioObtenerVersionFinal.class);

        var servicio = new ServicioGenerarVersionFinal(requisitoRepositorioComando,requisitoRepositorioConsulta,faseRepositorioConsulta,ServicioObtenerVersionFinal);

        Mockito.when(requisitoRepositorioComando.actualizarVersion(Mockito.any(Version.class),Mockito.anyLong())).thenReturn(1L);

        Mockito.when(requisitoRepositorioConsulta.consultarVersionesPorEtapaID(Mockito.anyLong())).thenReturn(versionDTOS);
        Mockito.when(ServicioObtenerVersionFinal.ejecutar(Mockito.anyList())).thenReturn(versionDto);
        Mockito.when(requisitoRepositorioConsulta.consultarVersionPorID(Mockito.anyLong())).thenReturn(versionDto);

        Assertions.assertEquals(Mensajes.NO_EXISTE_ETAPA_CON_EL_ID + 1, Assertions.assertThrows(NullPointerException.class, ()
                -> servicio.ejecutar(1L)).getMessage());

    }
    @Test
    void FallaNoExisteVersion()
    {
        var EtapaDto = new EtapaDTO();

        var requisitoRepositorioComando = Mockito.mock(RequisitoRepositorioComando.class);
        var requisitoRepositorioConsulta= Mockito.mock(RequisitoRepositorioConsulta.class);
        var faseRepositorioConsulta = Mockito.mock(FaseRepositorioConsulta.class);
        var ServicioObtenerVersionFinal= Mockito.mock(ServicioObtenerVersionFinal.class);

        var servicio = new ServicioGenerarVersionFinal(requisitoRepositorioComando,requisitoRepositorioConsulta,faseRepositorioConsulta,ServicioObtenerVersionFinal);

        Mockito.when(requisitoRepositorioComando.actualizarVersion(Mockito.any(Version.class),Mockito.anyLong())).thenReturn(1L);
        Mockito.when(faseRepositorioConsulta.consultarEtapaPorID(Mockito.anyLong())).thenReturn(EtapaDto);

        Assertions.assertEquals(Mensajes.NO_EXISTE_VERSION_CON_EL_ID , Assertions.assertThrows(AccionExcepcion.class, ()
                -> servicio.ejecutar(1L)).getMessage());

    }
}
