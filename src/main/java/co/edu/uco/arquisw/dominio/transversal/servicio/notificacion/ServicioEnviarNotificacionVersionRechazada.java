package co.edu.uco.arquisw.dominio.transversal.servicio.notificacion;

import co.edu.uco.arquisw.dominio.fase.dto.EtapaDTO;
import co.edu.uco.arquisw.dominio.fase.dto.FaseDTO;
import co.edu.uco.arquisw.dominio.fase.dto.proyecto.ProyectoDTO;
import co.edu.uco.arquisw.dominio.seleccion.dto.SeleccionDTO;
import co.edu.uco.arquisw.dominio.transversal.servicio.ServicioEnviarCorreoElectronico;
import co.edu.uco.arquisw.dominio.transversal.utilitario.Mensajes;
import co.edu.uco.arquisw.dominio.usuario.puerto.consulta.PersonaRepositorioConsulta;

import java.util.List;

public class ServicioEnviarNotificacionVersionRechazada extends ServicioEnviarNotificacion {

    public ServicioEnviarNotificacionVersionRechazada(ServicioEnviarCorreoElectronico servicioEnviarCorreoElectronico, PersonaRepositorioConsulta personaRepositorioConsulta) {
        super(servicioEnviarCorreoElectronico, personaRepositorioConsulta);
    }

    @Override
    public void notificar(List<SeleccionDTO> seleccionesDelProyecto, EtapaDTO etapa, FaseDTO fase, ProyectoDTO proyecto, Long versionId, String motivoRechazo) {
        seleccionesDelProyecto.forEach(seleccionDelProyecto -> {
            var correo = this.getPersonaRepositorioConsulta().consultarPorId(seleccionDelProyecto.getUsuarioID()).getCorreo();
            var asunto = Mensajes.VERSION_DE_LA_ETAPA_RECHAZADA;
            var cuerpo = Mensajes.obtenerCuerpoNotificacionVersionRechazada(versionId, etapa.getNombre(), fase.getNombre(), proyecto.getNombre(), motivoRechazo);

            this.enviarNotificacion(correo, asunto, cuerpo);
        });
    }
}