package co.edu.uco.arquisw.dominio.etapa.servicio;


import co.edu.uco.arquisw.dominio.fase.dto.EtapaDTO;
import co.edu.uco.arquisw.dominio.fase.dto.FaseDTO;
import co.edu.uco.arquisw.dominio.fase.modelo.Fase;
import co.edu.uco.arquisw.dominio.fase.puerto.comando.FaseRepositorioComando;
import co.edu.uco.arquisw.dominio.fase.puerto.comando.ProyectoRepositorioComando;
import co.edu.uco.arquisw.dominio.fase.puerto.consulta.FaseRepositorioConsulta;
import co.edu.uco.arquisw.dominio.fase.puerto.consulta.ProyectoRepositorioConsulta;
import co.edu.uco.arquisw.dominio.fase.servicio.ServicioAprobarEtapa;
import co.edu.uco.arquisw.dominio.requisito.puerto.comando.RequisitoRepositorioComando;
import co.edu.uco.arquisw.dominio.requisito.puerto.consulta.RequisitoRepositorioConsulta;
import co.edu.uco.arquisw.dominio.seleccion.puerto.consulta.SeleccionRepositorioConsulta;
import co.edu.uco.arquisw.dominio.transversal.excepciones.AccionExcepcion;
import co.edu.uco.arquisw.dominio.transversal.servicio.ServicioActualizarToken;
import co.edu.uco.arquisw.dominio.transversal.servicio.ServicioEnviarCorreoElectronico;
import co.edu.uco.arquisw.dominio.transversal.utilitario.Mensajes;
import co.edu.uco.arquisw.dominio.usuario.puerto.consulta.PersonaRepositorioComando;
import co.edu.uco.arquisw.dominio.usuario.puerto.consulta.PersonaRepositorioConsulta;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class ServicioAprobarEtapaTest {
    @Test
    void aprobarFallaNoExisteEtapa() {
        var EtapaDto = new EtapaDTO();
        var faseDto = new FaseDTO();
        EtapaDto.setNombre("NOMBRE");
        EtapaDto.setDescripcion("descripcion");

        var faseRepositorioComando = Mockito.mock(FaseRepositorioComando.class);
        var faseRepositorioConsulta = Mockito.mock(FaseRepositorioConsulta.class);
        var seleccionRepositorioConsulta = Mockito.mock(SeleccionRepositorioConsulta.class);
        var proyectoRepositorioConsulta = Mockito.mock(ProyectoRepositorioConsulta.class);
        var proyectoRepositorioComando = Mockito.mock(ProyectoRepositorioComando.class);
        var personaRepositorioConsulta = Mockito.mock(PersonaRepositorioConsulta.class);
        var personaRepositorioComando = Mockito.mock(PersonaRepositorioComando.class);
        var servicioEnviarCorreoElectronico = Mockito.mock(ServicioEnviarCorreoElectronico.class);
        var requisitoRepositorioConsulta = Mockito.mock(RequisitoRepositorioConsulta.class);
        var requisitoRepositorioComando = Mockito.mock(RequisitoRepositorioComando.class);
        var servicioActualizarToken = Mockito.mock(ServicioActualizarToken.class);

        var servicio = new ServicioAprobarEtapa(faseRepositorioComando, faseRepositorioConsulta, seleccionRepositorioConsulta, proyectoRepositorioConsulta, proyectoRepositorioComando, personaRepositorioConsulta, personaRepositorioComando, servicioEnviarCorreoElectronico, requisitoRepositorioConsulta, requisitoRepositorioComando, servicioActualizarToken);

        Mockito.when(faseRepositorioComando.guardar(Mockito.any(Fase.class), Mockito.anyLong())).thenReturn(1L);
        Mockito.when(faseRepositorioConsulta.consultarFasePorEtapaID(Mockito.anyLong())).thenReturn(faseDto);

        Assertions.assertEquals(Mensajes.NO_EXISTE_ETAPA_CON_EL_ID + 1, Assertions.assertThrows(NullPointerException.class, ()
                -> servicio.ejecutar(1L)).getMessage());
    }

    @Test
    void aprobarFallaEtapaCompleta() {
        var EtapaDto = new EtapaDTO();
        var faseDto = new FaseDTO();
        EtapaDto.setId(1L);
        EtapaDto.setNombre("NOMBRE");
        EtapaDto.setDescripcion("descripcion");
        EtapaDto.setCompletada(true);

        var faseRepositorioComando = Mockito.mock(FaseRepositorioComando.class);
        var faseRepositorioConsulta = Mockito.mock(FaseRepositorioConsulta.class);
        var seleccionRepositorioConsulta = Mockito.mock(SeleccionRepositorioConsulta.class);
        var proyectoRepositorioConsulta = Mockito.mock(ProyectoRepositorioConsulta.class);
        var proyectoRepositorioComando = Mockito.mock(ProyectoRepositorioComando.class);
        var personaRepositorioConsulta = Mockito.mock(PersonaRepositorioConsulta.class);
        var personaRepositorioComando = Mockito.mock(PersonaRepositorioComando.class);
        var servicioEnviarCorreoElectronico = Mockito.mock(ServicioEnviarCorreoElectronico.class);
        var requisitoRepositorioConsulta = Mockito.mock(RequisitoRepositorioConsulta.class);
        var requisitoRepositorioComando = Mockito.mock(RequisitoRepositorioComando.class);
        var servicioActualizarToken = Mockito.mock(ServicioActualizarToken.class);

        var servicio = new ServicioAprobarEtapa(faseRepositorioComando, faseRepositorioConsulta, seleccionRepositorioConsulta, proyectoRepositorioConsulta, proyectoRepositorioComando, personaRepositorioConsulta, personaRepositorioComando, servicioEnviarCorreoElectronico, requisitoRepositorioConsulta, requisitoRepositorioComando, servicioActualizarToken);

        Mockito.when(faseRepositorioComando.guardar(Mockito.any(Fase.class), Mockito.anyLong())).thenReturn(1L);
        Mockito.when(faseRepositorioConsulta.consultarEtapaPorID(Mockito.anyLong())).thenReturn(EtapaDto);
        Mockito.when(faseRepositorioConsulta.consultarFasePorEtapaID(Mockito.anyLong())).thenReturn(faseDto);

        Assertions.assertEquals(Mensajes.ETAPA_CON_ID + 1 + Mensajes.ETAPA_YA_ESTA_COMPLETADA, Assertions.assertThrows(AccionExcepcion.class, ()
                -> servicio.ejecutar(1L)).getMessage());
    }
}
