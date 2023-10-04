package co.edu.uco.arquisw.dominio.transversal.servicio.notificacion;

import co.edu.uco.arquisw.dominio.fase.dto.EtapaDTO;
import co.edu.uco.arquisw.dominio.fase.dto.FaseDTO;
import co.edu.uco.arquisw.dominio.fase.dto.proyecto.ProyectoDTO;
import co.edu.uco.arquisw.dominio.seleccion.dto.SeleccionDTO;
import co.edu.uco.arquisw.dominio.transversal.servicio.ServicioEnviarCorreoElectronico;
import co.edu.uco.arquisw.dominio.transversal.utilitario.Mensajes;
import co.edu.uco.arquisw.dominio.transversal.utilitario.TextoConstante;
import co.edu.uco.arquisw.dominio.usuario.puerto.consulta.PersonaRepositorioConsulta;

import java.util.List;

public class ServicioEnviarNotificacionVersionFinalGenerada extends ServicioEnviarNotificacion {
    public ServicioEnviarNotificacionVersionFinalGenerada(ServicioEnviarCorreoElectronico servicioEnviarCorreoElectronico, PersonaRepositorioConsulta personaRepositorioConsulta) {
        super(servicioEnviarCorreoElectronico, personaRepositorioConsulta);
    }

    @Override
    public void notificar(List<SeleccionDTO> seleccionesDelProyecto, EtapaDTO etapa, FaseDTO fase, ProyectoDTO proyecto, Long versionId, String motivoRechazo) {
        seleccionesDelProyecto.forEach(seleccionDelProyecto -> {
            if (seleccionDelProyecto.getRoles().contains(TextoConstante.ROL_LIDER_DEL_EQUIPO)) {
                var correo = this.getPersonaRepositorioConsulta().consultarPorId(seleccionDelProyecto.getUsuarioID()).getCorreo();
                var asunto = Mensajes.VERSION_FINAL_GENERADA_Y_LISTA_PARA_REVISION;
                var cuerpo = Mensajes.obtenerCuerpoNotificacionVersionFinalGenerada(versionId, etapa.getNombre(), fase.getNombre(), proyecto.getNombre());

                this.enviarNotificacion(correo, asunto, cuerpo);
            }
        });
    }
}