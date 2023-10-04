package co.edu.uco.arquisw.dominio.requisito.servicio;

import co.edu.uco.arquisw.dominio.fase.dto.EtapaDTO;
import co.edu.uco.arquisw.dominio.fase.dto.FaseDTO;
import co.edu.uco.arquisw.dominio.fase.dto.proyecto.ProyectoDTO;
import co.edu.uco.arquisw.dominio.fase.puerto.consulta.FaseRepositorioConsulta;
import co.edu.uco.arquisw.dominio.fase.puerto.consulta.ProyectoRepositorioConsulta;
import co.edu.uco.arquisw.dominio.requisito.modelo.MotivoRechazoVersion;
import co.edu.uco.arquisw.dominio.requisito.modelo.Version;
import co.edu.uco.arquisw.dominio.requisito.puerto.comando.RequisitoRepositorioComando;
import co.edu.uco.arquisw.dominio.requisito.puerto.consulta.RequisitoRepositorioConsulta;
import co.edu.uco.arquisw.dominio.seleccion.dto.SeleccionDTO;
import co.edu.uco.arquisw.dominio.seleccion.puerto.consulta.SeleccionRepositorioConsulta;
import co.edu.uco.arquisw.dominio.transversal.servicio.ServicioEnviarCorreoElectronico;
import co.edu.uco.arquisw.dominio.transversal.utilitario.LogicoConstante;
import co.edu.uco.arquisw.dominio.transversal.utilitario.Mensajes;
import co.edu.uco.arquisw.dominio.transversal.validador.ValidarObjeto;
import co.edu.uco.arquisw.dominio.usuario.puerto.consulta.PersonaRepositorioConsulta;
import lombok.AllArgsConstructor;

import javax.mail.MessagingException;
import java.util.List;

@AllArgsConstructor
public class ServicioRechazarVersionPorID {
    private final RequisitoRepositorioComando requisitoRepositorioComando;
    private final RequisitoRepositorioConsulta requisitoRepositorioConsulta;
    private final FaseRepositorioConsulta faseRepositorioConsulta;
    private final SeleccionRepositorioConsulta seleccionRepositorioConsulta;
    private final ProyectoRepositorioConsulta proyectoRepositorioConsulta;
    private final PersonaRepositorioConsulta personaRepositorioConsulta;
    private final ServicioEnviarCorreoElectronico servicioEnviarCorreoElectronico;
    private final ServicioObtenerRequisitosEtapaAnterior servicioObtenerRequisitosEtapaAnterior;
    private final ServicioGuardarRequisitos servicioGuardarRequisitos;

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

        notificar(seleccionesDelProyecto, versionId, etapa, fase, proyecto, motivoRechazoVersion);

        return respuestaId;
    }

    private void validarSiExisteVersionConID(Long versionId) {
        if (ValidarObjeto.esNulo(this.requisitoRepositorioConsulta.consultarVersionPorID(versionId))) {
            throw new NullPointerException(Mensajes.NO_EXISTE_VERSION_CON_EL_ID + versionId);
        }
    }

    private void notificar(List<SeleccionDTO> seleccionesDelProyecto, Long versionId, EtapaDTO etapa, FaseDTO fase, ProyectoDTO proyecto, MotivoRechazoVersion motivoRechazoVersion) {
        seleccionesDelProyecto.forEach(seleccionDelProyecto -> {
            try {
                var correo = this.personaRepositorioConsulta.consultarPorId(seleccionDelProyecto.getUsuarioID()).getCorreo();
                var asunto = Mensajes.VERSION_DE_LA_ETAPA_RECHAZADA;
                var cuerpo = Mensajes.LA_VERSION + versionId + Mensajes.DE_LA_ETAPA + etapa.getNombre() + Mensajes.DE_LA_FASE + fase.getNombre() + Mensajes.EN_EL_PROYECTO + proyecto.getNombre() + Mensajes.HA_SIDO_RECHAZADA_POR_EL_LIDER_DE_EQUIPO_CUYO_MOTIVO_ES + motivoRechazoVersion.getMotivo();

                this.servicioEnviarCorreoElectronico.enviarCorreo(correo, asunto, cuerpo);
            } catch (MessagingException e) {
                throw new RuntimeException(e.getMessage());
            }
        });
    }
}