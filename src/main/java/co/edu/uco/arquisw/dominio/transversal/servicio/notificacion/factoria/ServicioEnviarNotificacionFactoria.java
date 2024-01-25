package co.edu.uco.arquisw.dominio.transversal.servicio.notificacion.factoria;

import co.edu.uco.arquisw.dominio.fase.dto.EtapaDTO;
import co.edu.uco.arquisw.dominio.fase.dto.FaseDTO;
import co.edu.uco.arquisw.dominio.fase.dto.proyecto.ProyectoDTO;
import co.edu.uco.arquisw.dominio.seleccion.dto.SeleccionDTO;
import co.edu.uco.arquisw.dominio.transversal.servicio.notificacion.ServicioEnviarNotificacionAprobacionEtapa;
import co.edu.uco.arquisw.dominio.transversal.servicio.notificacion.ServicioEnviarNotificacionVersionFinalGenerada;
import co.edu.uco.arquisw.dominio.transversal.servicio.notificacion.ServicioEnviarNotificacionVersionInicialGuardada;
import co.edu.uco.arquisw.dominio.transversal.servicio.notificacion.ServicioEnviarNotificacionVersionRechazada;
import co.edu.uco.arquisw.dominio.transversal.servicio.notificacion.enumerador.TipoNotificacion;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@AllArgsConstructor
public class ServicioEnviarNotificacionFactoria {
    private final ServicioEnviarNotificacionAprobacionEtapa servicioEnviarNotificacionAprobacionEtapa;
    private final ServicioEnviarNotificacionVersionFinalGenerada servicioEnviarNotificacionVersionFinalGenerada;
    private final ServicioEnviarNotificacionVersionInicialGuardada servicioEnviarNotificacionVersionInicialGuardada;
    private final ServicioEnviarNotificacionVersionRechazada servicioEnviarNotificacionVersionRechazada;

    @Async
    @Transactional
    public void orquestarNotificacion(List<SeleccionDTO> seleccionesDelProyecto, EtapaDTO etapa, FaseDTO fase, ProyectoDTO proyecto, Long versionId, String motivoRechazo, TipoNotificacion tipoNotificacion) {
        switch (tipoNotificacion) {
            case APROBACION_ETAPA ->
                    this.servicioEnviarNotificacionAprobacionEtapa.notificar(seleccionesDelProyecto, etapa, fase, proyecto, versionId, motivoRechazo);
            case VERSION_FINAL_GENERADA ->
                    this.servicioEnviarNotificacionVersionFinalGenerada.notificar(seleccionesDelProyecto, etapa, fase, proyecto, versionId, motivoRechazo);
            case VERSION_INICIAL_GUARDADA ->
                    this.servicioEnviarNotificacionVersionInicialGuardada.notificar(seleccionesDelProyecto, etapa, fase, proyecto, versionId, motivoRechazo);
            case VERSION_RECHAZADA ->
                    this.servicioEnviarNotificacionVersionRechazada.notificar(seleccionesDelProyecto, etapa, fase, proyecto, versionId, motivoRechazo);
        }
    }
}