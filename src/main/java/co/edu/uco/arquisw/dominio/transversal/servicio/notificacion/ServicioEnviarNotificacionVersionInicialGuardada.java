package co.edu.uco.arquisw.dominio.transversal.servicio.notificacion;

import co.edu.uco.arquisw.dominio.fase.dto.EtapaDTO;
import co.edu.uco.arquisw.dominio.fase.dto.FaseDTO;
import co.edu.uco.arquisw.dominio.fase.dto.proyecto.ProyectoDTO;
import co.edu.uco.arquisw.dominio.seleccion.dto.SeleccionDTO;
import co.edu.uco.arquisw.dominio.transversal.servicio.ServicioEnviarCorreoElectronico;
import co.edu.uco.arquisw.dominio.transversal.utilitario.Mensajes;
import co.edu.uco.arquisw.dominio.usuario.puerto.consulta.PersonaRepositorioConsulta;

import java.util.List;

public class ServicioEnviarNotificacionVersionInicialGuardada extends ServicioEnviarNotificacion {
    public ServicioEnviarNotificacionVersionInicialGuardada(ServicioEnviarCorreoElectronico servicioEnviarCorreoElectronico, PersonaRepositorioConsulta personaRepositorioConsulta) {
        super(servicioEnviarCorreoElectronico, personaRepositorioConsulta);
    }

    @Override
    public void notificar(List<SeleccionDTO> seleccionesDelProyecto, EtapaDTO etapa, FaseDTO fase, ProyectoDTO proyecto, Long versionId, String motivoRechazo) {
        seleccionesDelProyecto.forEach(seleccionDelProyecto -> {
            var correo = this.getPersonaRepositorioConsulta().consultarPorId(seleccionDelProyecto.getUsuarioID()).getCorreo();
            var asunto = Mensajes.PRIMERA_VERSION_DE_LA_ETAPA_INICIADA;
            var cuerpo = Mensajes.obtenerCuerpoNotificacionVersionInicialGuardada(etapa.getNombre(), fase.getNombre(), proyecto.getNombre());

            this.enviarNotificacion(correo, asunto, cuerpo);
        });
    }
}