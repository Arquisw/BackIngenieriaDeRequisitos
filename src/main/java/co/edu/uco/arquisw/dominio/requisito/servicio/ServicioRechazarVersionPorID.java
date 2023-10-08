package co.edu.uco.arquisw.dominio.requisito.servicio;

import co.edu.uco.arquisw.dominio.fase.puerto.consulta.FaseRepositorioConsulta;
import co.edu.uco.arquisw.dominio.fase.puerto.consulta.ProyectoRepositorioConsulta;
import co.edu.uco.arquisw.dominio.requisito.modelo.MotivoRechazoVersion;
import co.edu.uco.arquisw.dominio.requisito.modelo.Version;
import co.edu.uco.arquisw.dominio.requisito.puerto.comando.RequisitoRepositorioComando;
import co.edu.uco.arquisw.dominio.requisito.puerto.consulta.RequisitoRepositorioConsulta;
import co.edu.uco.arquisw.dominio.seleccion.puerto.consulta.SeleccionRepositorioConsulta;
import co.edu.uco.arquisw.dominio.transversal.servicio.notificacion.factoria.ServicioEnviarNotificacionFactoria;
import co.edu.uco.arquisw.dominio.transversal.utilitario.LogicoConstante;
import co.edu.uco.arquisw.dominio.transversal.utilitario.Mensajes;
import co.edu.uco.arquisw.dominio.transversal.validador.ValidarObjeto;
import lombok.AllArgsConstructor;

import static co.edu.uco.arquisw.dominio.transversal.servicio.notificacion.enumerador.TipoNotificacion.VERSION_RECHAZADA;

@AllArgsConstructor
public class ServicioRechazarVersionPorID {
    private final RequisitoRepositorioComando requisitoRepositorioComando;
    private final RequisitoRepositorioConsulta requisitoRepositorioConsulta;
    private final FaseRepositorioConsulta faseRepositorioConsulta;
    private final SeleccionRepositorioConsulta seleccionRepositorioConsulta;
    private final ProyectoRepositorioConsulta proyectoRepositorioConsulta;
    private final ServicioObtenerRequisitosEtapaAnterior servicioObtenerRequisitosEtapaAnterior;
    private final ServicioGuardarRequisitos servicioGuardarRequisitos;
    private final ServicioEnviarNotificacionFactoria servicioEnviarNotificacionFactoria;

    public Long ejecutar(MotivoRechazoVersion motivoRechazoVersion, Long versionId) {
        validarSiExisteVersionConID(versionId);

        var versionDTO = this.requisitoRepositorioConsulta.consultarVersionPorID(versionId);
        var etapaID = versionDTO.getEtapaID();
        var etapa = this.faseRepositorioConsulta.consultarEtapaPorID(etapaID);
        var fase = this.faseRepositorioConsulta.consultarFasePorEtapaID(etapaID);
        var proyectoId = fase.getProyectoID();

        this.servicioObtenerRequisitosEtapaAnterior.ejecutar(
                etapa,
                versionId,
                proyectoId,
                Mensajes.NO_HAY_REQUISITOS_PARA_LA_VERSION_ACTUAL_NO_PUEDE_RECHAZAR,
                Mensajes.LA_VERSION_NO_HA_SUFRIDO_NINGUN_CAMBIO_CON_RESPECTO_A_LA_ETAPA_ANTERIOR_NO_PUEDE_RECHAZAR
        );

        var seleccionesDelProyecto = this.seleccionRepositorioConsulta.consultarSeleccionadosPorProyecto(proyectoId);
        var proyecto = this.proyectoRepositorioConsulta.consultarProyectoPorID(proyectoId);

        this.requisitoRepositorioComando.guardarMotivoRechazoVersion(motivoRechazoVersion, versionId);
        this.requisitoRepositorioComando.actualizarVersion(LogicoConstante.ESTADO_VERSION_POR_DEFECTO, LogicoConstante.ESTA_RECHAZADA, versionId);

        var nuevaVersion = Version.crear(LogicoConstante.ESTADO_VERSION_POR_DEFECTO, LogicoConstante.NO_ESTA_RECHAZADA);

        var respuestaId = this.requisitoRepositorioComando.guardarVersion(nuevaVersion, etapaID);

        this.servicioGuardarRequisitos.guardarRequisitosSegunEtapa(etapa, respuestaId, proyectoId);

        this.servicioEnviarNotificacionFactoria.orquestarNotificacion(seleccionesDelProyecto, etapa, fase, proyecto, versionId, versionDTO.getMotivoRechazo(), VERSION_RECHAZADA);

        return respuestaId;
    }

    private void validarSiExisteVersionConID(Long versionId) {
        if (ValidarObjeto.esNulo(this.requisitoRepositorioConsulta.consultarVersionPorID(versionId))) {
            throw new NullPointerException(Mensajes.obtenerNoExiteVersionConId(versionId));
        }
    }
}