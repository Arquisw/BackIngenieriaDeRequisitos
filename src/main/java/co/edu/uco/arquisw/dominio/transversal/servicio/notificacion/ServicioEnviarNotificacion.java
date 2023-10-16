package co.edu.uco.arquisw.dominio.transversal.servicio.notificacion;

import co.edu.uco.arquisw.dominio.fase.dto.EtapaDTO;
import co.edu.uco.arquisw.dominio.fase.dto.FaseDTO;
import co.edu.uco.arquisw.dominio.fase.dto.proyecto.ProyectoDTO;
import co.edu.uco.arquisw.dominio.seleccion.dto.SeleccionDTO;
import co.edu.uco.arquisw.dominio.transversal.excepciones.EnvioNotificacionesExcepcion;
import co.edu.uco.arquisw.dominio.transversal.servicio.ServicioEnviarCorreoElectronico;
import co.edu.uco.arquisw.dominio.usuario.puerto.consulta.PersonaRepositorioConsulta;
import lombok.Getter;

import javax.mail.MessagingException;
import java.util.List;

@Getter
public abstract class ServicioEnviarNotificacion {
    private final ServicioEnviarCorreoElectronico servicioEnviarCorreoElectronico;
    private final PersonaRepositorioConsulta personaRepositorioConsulta;

    protected ServicioEnviarNotificacion(ServicioEnviarCorreoElectronico servicioEnviarCorreoElectronico, PersonaRepositorioConsulta personaRepositorioConsulta) {
        this.servicioEnviarCorreoElectronico = servicioEnviarCorreoElectronico;
        this.personaRepositorioConsulta = personaRepositorioConsulta;
    }

    public abstract void notificar(List<SeleccionDTO> seleccionesDelProyecto, EtapaDTO etapa, FaseDTO fase, ProyectoDTO proyecto, Long versionId, String motivoRechazo);

    public void enviarNotificacion(String correo, String asunto, String cuerpo) {
        try {
            this.servicioEnviarCorreoElectronico.enviarCorreo(correo, asunto, cuerpo);
        } catch (MessagingException e) {
            throw new EnvioNotificacionesExcepcion(e.getMessage());
        }
    }
}